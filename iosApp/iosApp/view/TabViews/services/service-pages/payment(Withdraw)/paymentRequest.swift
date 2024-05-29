//
//  paymentRequest.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 29/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct PaymentRequest: View {
    @State private var accountCode = ""
    @State private var amount = ""
    @State private var description = ""
    
    var body: some View {
        VStack() {
            inputView(text: $accountCode, title: "Account Code", placeHolder: "Select account code")
                .padding(.top, 20)
            inputView(text: $amount, title: "Amount", placeHolder: "Enter amount")
                .padding(.top, 10)
            inputView(text: $description, title: "Description", placeHolder: "Write description")
                .padding(.top, 10)
            
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
        //.padding(.top, 40)
        .navigationBarTitle("Withdraw Request", displayMode: .inline)
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

struct PaymentRequest_Previews: PreviewProvider {
    static var previews: some View {
        PaymentRequest()
    }
}
