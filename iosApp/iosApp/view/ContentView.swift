import SwiftUI

struct ContentView: View {
    @ObservedObject var viewModel: ContentViewModel
    
    init() {
        
        self.viewModel = ContentViewModel()
    }
    
    var body: some View {
        VStack {
            Text("Account Balances").font(.title)
            Text("accountBalanceList: \(viewModel.accountBalanceList.count)")
            if viewModel.accountBalances.isEmpty {
                Text("No account balances found.")
            } else {
                List(viewModel.accountBalances, id: \.accountCode) { balance in
                    VStack(alignment: .leading) {
                        Text("Account Code: \(balance.accountCode)")
                        Text("Current Balance: \(balance.currentBalance)")
                        // Add more properties as needed
                    }
                }
            }
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
