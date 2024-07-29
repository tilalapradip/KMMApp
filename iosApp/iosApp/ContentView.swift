import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
//	let greet = Greeting().greeting()

	var body: some View {
//        List{
//            
//            Text("Hello, SwiftUI!")
//                .font(.headline)
//                .border(Color.pink)
//                // 1
//                .frame(maxWidth: .infinity)
//
//                // 2
//                .border(Color.blue)
//            Text("Hello, SwiftUI!")
//                .font(.headline)
//                .border(Color.pink)
//                // 1
//                .frame(maxWidth: .infinity)
//
//                // 2
//                .border(Color.blue)
//            Text("Hello, SwiftUI!")
//                .font(.headline)
//                .border(Color.pink)
//                // 1
//                .frame(maxWidth: .infinity,alignment: .leading)
//
//                // 2
//                .border(Color.blue)
//        }.listStyle(.plain).listRowInsets(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0))
        MobileListView(mobiles: viewModel.text).frame(maxWidth: .infinity).border(Color.blue)
//        Text(greet)
//        Text(viewModel.text)
//        MobileListView(mobiles: [
//            MobileItem(id: "1", name: "Samsung M31", data: nil),
//            MobileItem(id: "2", name: "Samsung M32", data: nil)])
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
//        @Published var text: Array<MobileItem> = "Loading..."
        @Published var text: Array<MobileItem> = Array()
        
        init() {
            Greeting().greeting { greeting, error in
                DispatchQueue.main.async {
                    if let greeting = greeting {
                        self.text = greeting
                    } else {
//                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}


struct MobileListView: View {
    let mobiles: Array<MobileItem>
    var body: some View {
        List(mobiles, id: \.self){ mobile in
//            ForEach(mobiles) { mobile in
            let newText = if(mobile.data != nil && mobile.data!.isEmpty==false){
                mobile.data!.values.joined(separator: ",")
            }else{
                "EMPTY RESPONE"
            }
            
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
                    
                    Text(
                        newText
                    )
                }.padding(EdgeInsets(top: 8, leading: 8, bottom: 8, trailing: 8))
                    .frame(maxWidth: .infinity,alignment: .leading)
            }).listRowInsets(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16)).listRowSeparator(.hidden)
        }.listStyle(PlainListStyle())
    }
    }

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//        ContentView(viewModel: ContentView.ViewModel())
////		ContentView()
//	}
//}
