import SwiftUI
import shared

struct ContentView: View {
    @State
    var viewModel: MobileListViewModel?
    @State
    var mobileDataState: MobileDataState = .Initial.shared
    
    var body: some View {
        NavigationStack {
            MobileListContent(state: mobileDataState).task {
                let viewModel = MobileListViewModel()
                await withTaskCancellationHandler {
                    self.viewModel = viewModel
                    Task {
                        try? viewModel.getMobilesFake()
                    }
                    
                    for await mobileDataState in viewModel.mobileDataState {
                        self.mobileDataState = mobileDataState
                    }
                } onCancel: {
                    
                }
                
            }.navigationTitle("Product List")
        }
    }
}

struct MobileListContent:View {
    var state: MobileDataState
    
    var body: some View {
        ZStack{
            VStack {
                switch onEnum(of: state) {
                case .initial:
                    Text(
                        "Initial"
                    )
                case .loading:
                    Text(
                        "Loading..."
                    )
                case .success(let data):
                    MobileListView(mobiles: data.data)
                case .failure:
                    Text(
                        "Failure"
                    )
                }
            }
        }
    }
}

struct MobileListView: View {
    let mobiles: Array<MobileItem>
    var body: some View {
        List(mobiles, id: \.self){ mobile in
            MobileRow(mobile: mobile)
        }.listStyle(PlainListStyle())
    }
}

struct MobileRow: View {
    let mobile: MobileItem
    var body: some View {
        ZStack(alignment: Alignment(horizontal: .leading,vertical: .center), content: {
            RoundedRectangle(cornerRadius: 8)
                .fill(.white)
                .shadow(radius: 4)
                .frame(maxWidth: .infinity)
            
            VStack(alignment: .leading){
                Text(
                    mobile.id
                )
                
                Text(
                    mobile.name
                )
            }.padding(EdgeInsets(top: 8, leading: 8, bottom: 8, trailing: 8))
                .frame(maxWidth: .infinity,alignment: .leading)
        }).listRowInsets(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16)).listRowSeparator(.hidden)
    }
}


