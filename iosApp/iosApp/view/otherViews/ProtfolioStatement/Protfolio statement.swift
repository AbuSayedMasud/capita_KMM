import SwiftUI

struct Protfolio_statement: View {
    //@State private var selectedTabIndex = 0
    @State private var selectedTabIndex: Int
    let names = ["Position", "Balance", "Receivable"]
    
    // Initializer to set the default selected tab index
     init(defaultSelectedTabIndex: Int = 0) {
         _selectedTabIndex = State(initialValue: defaultSelectedTabIndex)
     }
    var body: some View {
        VStack {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(0..<names.count) { index in
                        Button(action: {
                            self.selectedTabIndex = index
                        }) {
                            VStack {
                                Text(self.names[index])
                                    .padding()
                                    .foregroundColor(self.selectedTabIndex == index ? .white : Color.white.opacity(0.5))
                                if self.selectedTabIndex == index {
                                    Rectangle()
                                        .frame(width: UIScreen.main.bounds.width / 3, height: 3)
                                        .foregroundColor(.white)
                                }
                            }
                            .frame(width: UIScreen.main.bounds.width/3, height: 68)
                        }
                    }
                }
                .background(appColor)
            }
            
            // Placeholder for content
            switch selectedTabIndex {
            case 0:
                positions()
            case 1:
                Balance()
            case 2:
                Receivable()
            default:
                Text("No Content")
            }
            
            Spacer() // Add space to push buttons to the bottom
        }
        .navigationBarTitle(Text("Protfolio Statement"), displayMode: .inline)
        .navigationBarItems(
            trailing:
            HStack(spacing: 16) { // Stack for buttons on the right
                Button(action: {
                    // Action for first button
                }) {
                    Image(uiImage: UIImage(named: "loading_nav_button")!)
                }
                Button(action: {
                    // Action for second button
                }) {
                    Image(uiImage: UIImage(named: "alarm_nav_button")!)
                }
            }
        )
    }
}


// Preview
#if DEBUG
struct ProtfolioStatement_Previews: PreviewProvider {
    static var previews: some View {
        Protfolio_statement()
    }
}
#endif
