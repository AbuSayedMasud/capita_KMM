import SwiftUI
import shared

struct ProfileView: View {
    
    @State private var isModeUp = true // Initial mode is "Up"
    @State private var isPopoverPresented = false // State to manage popover presentation
    //let popupTitles = ["F111", "F222", "F333"]
    
    
    @ObservedObject private var profileVModel: ProfileViewModel
    
    
    //    init(viewModel: ProfileViewModel) {
    //        self.viewModel = viewModel
    //    }
    
    
    
    init() {
        self.profileVModel = ProfileViewModel()
        
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                ScrollView {
                    VStack(spacing: 20) {
                        // User Profile Row
                        UserProfileRow(image: "person.crop.circle.fill", userName: profileVModel.userName, accountCode: profileVModel.accountCodes, boId: profileVModel.boId, isModeUp: $isModeUp, isPopoverPresented: $isPopoverPresented)
                        
                        // 5 Different Rows with Titles
                        //TitleRow(title: "Balances")
                        TitleRow(title: "Balances", balanceViewModel: AccountBalanceViewModel())

                            .onTapGesture {
                                // Show description for Row 1
                                print("Description for Row 1")
                            }
                        
                        TitleRow(title: "Position", balanceViewModel: AccountBalanceViewModel())
                            .onTapGesture {
                                // Show description for Row 2
                                print("Description for Row 2")
                            }
                        
                        NavigationLink(destination: Protfolio_statement()) {
                            SimpleTitleRow(title: "Portfolio Statement")
                        }
                        
                        NavigationLink(destination: marketView()) {
                            SimpleTitleRow(title: "Ledger Statement")
                        }
                        
                        // Logout Row
                        LogoutRow()
                        // Spacer to push content to the top
                        Spacer()
                    }
                    .padding()
                    .navigationBarTitle("Profile", displayMode: .inline)
                    .navigationBarItems(
                        leading:
                            Button(action: {
                                // Handle action for the first button
                            }) {
                                Image(uiImage: UIImage(named: "loading_nav_button")!)
                            },
                        trailing:
                            Button(action: {
                                // Handle action for the second button
                            }) {
                                Image(uiImage: UIImage(named: "alarm_nav_button")!)
                                
                            }
                    )
                }
                
                if isPopoverPresented {
                    VStack {
                        ForEach(profileVModel.popupTitles, id: \.self) { title in
                            HStack {
                                Text(title)
                                    .onTapGesture {
                                        // Update accountCodes when a popup title is selected
                                        //self.accountCodes = title
                                        profileVModel.accountCodes = title
                                        self.isPopoverPresented.toggle() // Close the popup
                                    }
                            }
                            .padding()
                        }
                    }
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    .frame(width: UIScreen.main.bounds.width - 20) // Set fixed width for the popup
                    .position(x: UIScreen.main.bounds.width / 2, y: 150) // Position the popup above the rows
                    .onTapGesture {
                        // Close the popup when tapped anywhere outside of it
                        self.isPopoverPresented.toggle()
                    }
                }
            }
        }
    }
}

struct UserProfileRow: View {
    var image: String
    var userName: String
    var accountCode: String
    var boId: String
    @Binding var isModeUp: Bool
    @Binding var isPopoverPresented: Bool
    
    @State private var selectedImage: UIImage?
    @State private var isImagePickerPresented = false
    
    let rowHeight: CGFloat = 70 // Fixed height for the row
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Button(action: {
                    self.isImagePickerPresented.toggle()
                }) {
                    if let selectedImage = selectedImage {
                        Image(uiImage: selectedImage)
                            .resizable()
                            .frame(width: 50, height: 50)
                            .clipShape(Circle())
                    } else {
                        Image(systemName: image)
                            .resizable()
                            .frame(width: 50, height: 50)
                            .clipShape(Circle())
                    }
                }
                .sheet(isPresented: $isImagePickerPresented) {
                    ImagePicker(selectedImage: $selectedImage)
                }
                
                VStack(alignment: .leading) {
                    Text(userName)
                        .font(.headline)
                    
                    HStack {
                        Text("Account Code: \(accountCode)")
                        
                        // Mode buttons with popup
                        Button(action: {
                            self.isModeUp.toggle()
                            self.isPopoverPresented.toggle()
                        }) {
                            Image(systemName: isModeUp ? "chevron.up" : "chevron.down")
                        }
                        .buttonStyle(PlainButtonStyle()) // Remove button default style
                    }
                    
                    Text("BO ID: \(boId)")
                        .font(.subheadline)
                }
                Spacer()
            }
            .frame(height: rowHeight) // Set fixed height for the row
            .padding()
            .background(Color.white)
            .cornerRadius(10)
            .shadow(radius: 5)
        }
        .padding(.top, 20)
    }
}

struct TitleRow: View {
    
    var title: String
    
    @State private var isExpanded: Bool = false
    @ObservedObject var balanceViewModel: AccountBalanceViewModel
    
//    init(){
//        self.balanceViewModel = AccountBalanceViewModel()
//    }
    init(title: String, balanceViewModel: AccountBalanceViewModel) {
        self.title = title
        self.balanceViewModel = balanceViewModel
    }
    
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack {
                Text(title)
                    .font(.headline)
                Spacer()
                Image(systemName: isExpanded ? "chevron.up" : "chevron.down")
            }
            .padding()
            .background(Color.white)
            .cornerRadius(10)
            .shadow(radius: 5)
            .onTapGesture {
                isExpanded.toggle()
            }
            
            if isExpanded {
                
                // Additional rows with data
                VStack(alignment: .leading, spacing: 10) {
                    if title == "Balances" {
                        additionalBalanceRow(title: "Available Balance", value: String(balanceViewModel.cashBalance))
                        additionalBalanceRow(title: "Current Balance", value: String(balanceViewModel.currentBalance))
                        additionalBalanceRow(title: "Equity", value: String(balanceViewModel.equity))
                        additionalBalanceRow(title: "Purchase Power", value: String(balanceViewModel.buyingPower))
                    } else if title == "Position" {
                        additionalPositionRow(name: "ACI", marketPrice: "500.00", averagePrice: "500.00")
                        additionalPositionRow(name: "ACME", marketPrice: "500.00", averagePrice: "500.00")
                    }
                }
            }
        }
    }
    
    // Additional row for balance with title and value
    private func additionalBalanceRow(title: String, value: String) -> some View {
        HStack {
            Text(title)
                .font(.subheadline)
            Spacer()
            Text(value)
                .font(.subheadline)
        }
        .padding([.leading, .trailing, .top])
    }
    
    // Additional row for position with name, market price, and average price
    private func additionalPositionRow(name: String, marketPrice: String, averagePrice: String) -> some View {
        HStack {
            VStack(alignment: .leading) {
                Text(name)
                Text("Market Price")
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text("Average Price")
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
            Spacer()
            VStack(alignment: .trailing) {
                //Text(marketPrice)
                Text(marketPrice)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text(averagePrice)
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
        }
        .padding([.leading, .trailing,.top])
    }
}

struct SimpleTitleRow: View {
    var title: String
    
    var body: some View {
        HStack {
            Text(title)
                .font(.headline)
            Spacer()
            Image(systemName: "chevron.right")
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .shadow(radius: 5)
    }
}
struct LogoutRow: View {
    var body: some View {
        HStack {
            Text("Logout")
                .font(.headline)
                .foregroundColor(.red)
            Spacer()
            Image(systemName: "power")
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .padding(.bottom, 20)
        .shadow(radius: 5)
    }
}

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView()
        
        //        let profileService = CustomerProfileServiceImpl(databaseDriverFactory: DatabaseDriverFactory())
        //        let viewModel = ProfileViewModel(profileService: profileService)
        //        return ProfileView(viewModel: viewModel)
    }
}
