import SwiftUI
import shared

struct ProfileView: View {
    
    @State private var isModeUp = true // Initial mode is "Up"
    @State private var isPopoverPresented = false // State to manage popover
    @State private var showAlert = false // State to manage alert presentation
    //let popupTitles = ["F111", "F222", "F333"]
    
    
    @ObservedObject private var profileVModel: ProfileViewModel
    
    private var instrumentViewModel = InstrumentViewModel()
    
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
                        //                        //TitleRow(title: "Balances")
                        //                        TitleRow(title: "Balances", ViewModel: AccountBalanceViewModel())
                        //
                        //                            .onTapGesture {
                        //                                // Show description for Row 1
                        //                                print("Description for Row 1")
                        //                            }
                        //
                        //                        TitleRow(title: "Position", ViewModel: AccountBalanceViewModel(), positionViewModel: InstrumentViewModel)
                        //                            .onTapGesture {
                        //                                // Show description for Row 2
                        //                                print("Description for Row 2")
                        //                            }
                        // Balances Row
                        BalanceTitleRow(title: "Balances", balanceViewModel: AccountBalanceViewModel())
                            .onTapGesture {
                                print("Description for Row 1")
                            }
                        
                        // Position Row
                        PositionTitleRow(title: "Position", positionViewModel: instrumentViewModel)
                            .onTapGesture {
                                print("Description for Row 2")
                            }
                        
                        NavigationLink(destination: Protfolio_statement()) {
                            SimpleTitleRow(title: "Portfolio Statement")
                        }
                        
                        NavigationLink(destination: Ledger_Statement()) {
                            SimpleTitleRow(title: "Ledger Statement")
                        }
                        
                        // Logout Row
                        //LogoutRow()
                        LogoutRow(showAlert: $showAlert)
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
                    //.background(Color.white)
                    .background(Color(UIColor(named: "sectionTheme")!))
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
        .alert(isPresented: $showAlert) {
            Alert(
                title: Text("Logout"),
                message: Text("Are you sure you want to logout?"),
                primaryButton: .default(Text("Cancel")),
                secondaryButton: .destructive(Text("Logout")) {
                    // Handle logout action here
                    print("User logged out")
                    navigateToLogin()
                }
            )
        }
    }
    private func navigateToLogin() {
        if let window = UIApplication.shared.windows.first {
            window.rootViewController = UIHostingController(rootView: LoginView())
            window.makeKeyAndVisible()
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
            //.background(Color.white)
            .background(Color(UIColor(named: "sectionTheme")!))
            .cornerRadius(10)
            .shadow(radius: 5)
        }
        .padding(.top, 20)
    }
}
struct BalanceTitleRow: View {
    
    var title: String
    
    @State private var isExpanded: Bool = false
    @ObservedObject var balanceViewModel: AccountBalanceViewModel
    
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
            //.background(Color.white)
            .background(Color(UIColor(named: "sectionTheme")!))
            .cornerRadius(10)
            .shadow(radius: 5)
            .onTapGesture {
                isExpanded.toggle()
            }
            
            if isExpanded {
                
                // Additional rows with data
                VStack(alignment: .leading, spacing: 10) {
                    additionalBalanceRow(title: "Available Balance", value: String(balanceViewModel.cashBalance))
                    additionalBalanceRow(title: "Current Balance", value: String(balanceViewModel.currentBalance))
                    additionalBalanceRow(title: "Equity", value: String(balanceViewModel.equity))
                    additionalBalanceRow(title: "Purchase Power", value: String(balanceViewModel.buyingPower))
                }
            }
        }
    }
    //struct TitleRow: View {
    //
    //    var title: String
    //
    //    @State private var isExpanded: Bool = false
    //    @ObservedObject var balanceViewModel: AccountBalanceViewModel
    //    //@ObservedObject var instrumentProfileViewModel: InstrumentViewModel
    //    @ObservedObject var positionViewModel: InstrumentViewModel? // Optional position view model
    //
    //
    //    //    init(){
    //    //        self.balanceViewModel = AccountBalanceViewModel()
    //    //    }
    //    init(title: String, ViewModel: AccountBalanceViewModel, positionViewModel: InstrumentViewModel? = nil) {
    //        self.title = title
    //        self.balanceViewModel = ViewModel
    //        self.positionViewModel = positionViewModel
    //        //self.instrumentProfileViewModel =
    //    }
    //
    //
    //    var body: some View {
    //        VStack(alignment: .leading, spacing: 0) {
    //            HStack {
    //                Text(title)
    //                    .font(.headline)
    //                Spacer()
    //                Image(systemName: isExpanded ? "chevron.up" : "chevron.down")
    //            }
    //            .padding()
    //            .background(Color.white)
    //            .cornerRadius(10)
    //            .shadow(radius: 5)
    //            .onTapGesture {
    //                isExpanded.toggle()
    //            }
    //
    //            if isExpanded {
    //
    //                // Additional rows with data
    //                VStack(alignment: .leading, spacing: 10) {
    //                    if title == "Balances" {
    //                        additionalBalanceRow(title: "Available Balance", value: String(balanceViewModel.cashBalance))
    //                        additionalBalanceRow(title: "Current Balance", value: String(balanceViewModel.currentBalance))
    //                        additionalBalanceRow(title: "Equity", value: String(balanceViewModel.equity))
    //                        additionalBalanceRow(title: "Purchase Power", value: String(balanceViewModel.buyingPower))
    //                    } else if title == "Position" {
    //
    //                        additionalPositionRow(name: "ACI", marketPrice: "500.00", averagePrice: balanceViewModel.)
    //                        additionalPositionRow(name: "ACME", marketPrice: "500.00", averagePrice: "500.00")
    //                    }
    //                }
    //            }
    //        }
    //    }
    
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
}

struct PositionTitleRow: View {
    
    var title: String
    
    @State private var isExpanded: Bool = false
    @ObservedObject var positionViewModel: InstrumentViewModel
    
    
    init(title: String, positionViewModel: InstrumentViewModel) {
        self.title = title
        self.positionViewModel = positionViewModel
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
            //.background(Color.white)
            .background(Color(UIColor(named: "sectionTheme")!))
            .cornerRadius(10)
            .shadow(radius: 5)
            .onTapGesture {
                isExpanded.toggle()
            }
            
            if isExpanded {
                
                // Additional rows with data
                VStack(alignment: .leading, spacing: 10) {
                    
                    ForEach(positionViewModel.instrumentsList.indices, id: \.self) { index in
                        let instrument = positionViewModel.instrumentsList[index]
                        
                        if let symbole = instrument["symbole"] as? String,
                           let marketPrice = instrument["marketPrice"] as? Double,
                           let costPrice = instrument["costPrice"] as? Double,
                            let marketValue = instrument["marketValue"] as? Double,
                            let Quantity = instrument["quantity"] as? Double
                        {
                            additionalPositionRow(
                                name: symbole,
                                marketPrice: String(format: "%.2f", marketPrice),
                                costPrice: String(format: "%.2f", costPrice),
                                marketValue: String(format: "%.2f", marketValue),
                                Quantity: String(format: "%.2f", Quantity)
                            )
                        }
                    }
                    
                }
            }
        }
    }
    
    // Additional row for position with name, market price, and average price
    private func additionalPositionRow(name: String, marketPrice: String, costPrice: String,marketValue:String, Quantity: String) -> some View {
        HStack {
            VStack(alignment: .leading) {
                Text(name)
                Text("Cost Price")
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text("Market Price")
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text("Market Value")
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text("Quantity")
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
            Spacer()
            VStack(alignment: .trailing) {
                Text(costPrice)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text(marketPrice)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text(marketValue)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                Text(Quantity)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                
            }
        }
        .padding([.leading, .trailing, .top])
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
        .background(Color(UIColor(named: "sectionTheme")!))
        .cornerRadius(10)
        .shadow(radius: 5)
    }
}
struct LogoutRow: View {
    @Binding var showAlert: Bool
    var body: some View {
        HStack {
            Text("Logout")
                .font(.headline)
                .foregroundColor(.red)
            Spacer()
            Image(systemName: "power")
        }
        .padding()
        .background(Color(UIColor(named: "sectionTheme")!))
        .cornerRadius(10)
        .padding(.bottom, 20)
        .shadow(radius: 5)
        .onTapGesture {
            self.showAlert = true
        }
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
