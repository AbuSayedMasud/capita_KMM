import SwiftUI

struct Balance: View {
    // Define a variable to hold the amount
    @ObservedObject var AccountsVM: AccountBalanceViewModel
    
    init() {
        
        self.AccountsVM = AccountBalanceViewModel()
    }
    //let currentBalance: Double = AccountsVM.currentBalance
    
    var body: some View {
        VStack {
            // Add spacer to push content to the top
            
            HStack {
                Text("Current Balance")
                    .bold()
                Spacer()
                // Display the amount from the variable
                Text(String(AccountsVM.currentBalance))
                    .bold()
            }
            
            .padding()
            //.background(Color.white)
            .background(Color(UIColor(named: "sectionTheme")!))
            .cornerRadius(10)
            .shadow(radius: 5)
            .padding(.horizontal, 20)
            
            
            Spacer()
            
                
        }
    }
}

#Preview {
    Balance()
}
