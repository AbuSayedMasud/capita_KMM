import SwiftUI

struct Balance: View {
    // Define a variable to hold the amount
    let currentBalance: Double = 12856.12
    
    var body: some View {
        VStack {
            // Add spacer to push content to the top
            
            HStack {
                Text("Current Balance")
                    .bold()
                Spacer()
                // Display the amount from the variable
                Text("\(currentBalance)")
                    .bold()
            }
            
            .padding()
            .background(Color.white)
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
