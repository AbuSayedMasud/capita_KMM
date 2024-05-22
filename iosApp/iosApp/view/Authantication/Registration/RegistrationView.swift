//
//  RegistrationView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 5/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationView: View {
    @State private var username = ""
    @State private var firstName = ""
    @State private var lastName = ""
    @State private var email = ""
    @State private var mobileNumber = ""
    @State private var accountCode = ""
    @State private var password = ""
    @State private var confirmPassword = ""
    
    var body: some View {
        
        ZStack {
            Color.red
            
            ScrollView {
                VStack(spacing: 10) {
                    inputView(text: $username, title: "Username", placeHolder: "Username")
                    inputView(text: $firstName, title: "First Name", placeHolder: "First Name")
                    inputView(text: $lastName, title: "Last Name", placeHolder: "Last Name")
                    inputView(text: $email, title: "Email", placeHolder: "Email").autocapitalization(.none)
                    inputView(text: $mobileNumber, title: "Mobile Number", placeHolder: "Mobile Number")
                    inputView(text: $accountCode, title: "Account Code", placeHolder: "Account Code")
                    inputView(text: $password, title: "Password", placeHolder: "Password", isSecureField: true)
                    inputView(text: $confirmPassword, title: "Confirm Password", placeHolder: "Confirm Password", isSecureField: true)
                    
                    Button(action: {
                        print("Signing user in...")
                    }) {
                        Text("Sign Up")
                            .fontWeight(.semibold)
                            .foregroundColor(.primary)
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(Color("AccentColor"))
                            .cornerRadius(30)
                    }
                    .padding(.horizontal, 20)
                }
                .padding(.horizontal, 20)
                .padding(.top, 40)
                //.topSafeAreaColor()
            }
            //.background(Color.white)
            .background(Color(UIColor(named: "sectionTheme")!))
        }
        //.ignoresSafeArea(.all, edges: .vertical)
    }
}


struct RegistrationView_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationView()
    }
}
