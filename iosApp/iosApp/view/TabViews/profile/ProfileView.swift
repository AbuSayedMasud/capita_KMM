import SwiftUI

struct ProfileView: View {
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 20) {
                    // User Profile Row
                    UserProfileRow(image: "person.crop.circle.fill", userName: "Md. Momtajul Karim", accountCode: "F111", boId: "5678")
                    
                    // 5 Different Rows with Titles
                    TitleRow(title: "Balances")
                        .onTapGesture {
                            // Show description for Row 1
                            print("Description for Row 1")
                        }
                    
                    TitleRow(title: "Position")
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
        }
    }
}


struct UserProfileRow: View {
    var image: String
    var userName: String
    var accountCode: String
    var boId: String
    
    @State private var selectedImage: UIImage?
    @State private var isImagePickerPresented = false
    
    var body: some View {
        HStack {
            Button(action: {
                self.isImagePickerPresented.toggle()
            }) {
                if let selectedImage = selectedImage {
                    Image(uiImage: selectedImage)
                        .resizable()
                        .frame(width: 50, height: 50)
                        .clipShape(Circle()) // Apply circular clipping mask
                } else {
                    Image(systemName: image)
                        .resizable()
                        .frame(width: 50, height: 50)
                        .clipShape(Circle()) // Apply circular clipping mask
                }
            }
            .sheet(isPresented: $isImagePickerPresented) {
                ImagePicker(selectedImage: $selectedImage)
            }
            
            VStack(alignment: .leading) {
                Text(userName)
                    .font(.headline)
                
                Text("Account Code: \(accountCode)")
                Text("BO ID: \(boId)")
                    .font(.subheadline)
            }
            Spacer()
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .padding(.top, 20)
        .shadow(radius: 5)
    }
}

struct TitleRow: View {
    var title: String
    
    @State private var isExpanded: Bool = false
    
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
                        additionalBalanceRow(title: "Available Balance", value: "500.00")
                        additionalBalanceRow(title: "Current Balance", value: "500.00")
                        additionalBalanceRow(title: "Equity", value: "500.00")
                        additionalBalanceRow(title: "Purchase Power", value: "500.00")
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
    }
}

