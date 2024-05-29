import SwiftUI

struct DepositRequest: View {
    
    @State private var accountCode = ""
    @State private var amount = ""
    @State private var description = ""
    @State private var bankAccount = ""
    @State private var depositType = ""
    @State private var date = ""
    @State private var transectionRef = ""
    
    var body: some View {
        ScrollView {
            VStack() {
                inputView(text: $accountCode, title: "Account Code", placeHolder: "Select account code")
                    .padding(.top, 20)
                inputView(text: $bankAccount, title: "Bank Account", placeHolder: "Enter Bank Account")
                    .padding(.top, 10)
                inputView(text: $depositType, title: "Deposit Type", placeHolder: "Select deposit type")
                    .padding(.top, 10)
                inputView(text: $date, title: "Date", placeHolder: "DD/MM/YYYY")
                    .padding(.top, 10)
                inputView(text: $transectionRef, title: "Transection Ref.", placeHolder: "Enter Transection Reference")
                    .padding(.top, 10)
                inputView(text: $amount, title: "Amount", placeHolder: "Enter amount")
                    .padding(.top, 10)
                inputView(text: $description, title: "Description", placeHolder: "Write description")
                    .padding(.top, 10)
                
                // Image with buttons and labels inside the image border
                ZStack {
                    Image("image border")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 307 ,height: 220)
                    
                    VStack {
                        Image(uiImage: UIImage(named: "download-image-logo")!)
                            .padding(.top, 10)
                        Text("Let’s upload receipt")
                        Text("Upload your receipt for your deposit request from your gallery or open camera")
                            .font(.subheadline)
                            .fontWeight(.ultraLight)
                            .multilineTextAlignment(.center)
                            .padding(.horizontal, 25)
                            .foregroundColor(.secondary)
                        //Spacer()
                        HStack(spacing: 30) {
                            VStack {
                                Button(action: {
                                    // First button action
                                }) {
                                    Text("Label 1")
                                        .font(.caption)
                                }
                                
                            }
                            VStack {
                                Button(action: {
                                    // Second button action
                                }) {
                                    Text("Label 2")
                                        .font(.caption)
                                }
                                
                            }
                        }
                        .padding(.bottom, 10) // Adjust the padding as needed to fit within the image
                    }
                }
                .padding(.top, 20)
                
                Button(action: {
                    // Submit action
                }, label: {
                    Text("Submit")
                        .fontWeight(.semibold)
                        .foregroundColor(.white)
                        .frame(width: UIScreen.main.bounds.width - 70, height: 48)
                        .background(Color("AccentColor"))
                        .cornerRadius(12)
                })
                .padding()
                .padding(.top, 40)
                
                Spacer()
            }
            .padding(.horizontal, 20)
        }
        .navigationBarTitle("Deposit Request", displayMode: .inline)
        .navigationBarItems(
            leading: Button(action: {
                // Action for the first button
            }) {
                Image("loading_nav_button")
            },
            trailing: HStack {
                Button(action: {
                    // Action for the second button
                    // Show your alert or perform an action
                }) {
                    Image("search_nav_button")
                }
                Button(action: {
                    // Action for another button
                }) {
                    Image("alarm_nav_button")
                }
            }
        )
    }
}

#Preview {
    DepositRequest()
}
