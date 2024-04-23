//
//  password recovery.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct password_recovery: View {
    @State private var username = ""
    @State private var email = ""
    @State private var mobileNumber = ""
    @State private var selectedDate = Date()
    
    var body: some View {
        
        VStack(spacing: 20) {
            
            inputView(text: $username, title: "Username", placeHolder: "Username")
            
            HStack{
                inputView(text: .constant(selectedDate.formatted(dateFormatter)), title: "Date of Birth", placeHolder: "MM/DD/YYYY")
                DatePicker("", selection: $selectedDate, displayedComponents: .date)
                    .datePickerStyle(.compact)
            }
            
            inputView(text: $email, title: "Email", placeHolder: "Email").autocapitalization(.none)
            inputView(text: $mobileNumber, title: "Mobile Number", placeHolder: "Mobile Number")
            
            // Submit-button
            Button(action: {
                print("Forget submit button tapped ..")
            }) {
                Text("Submit")
                    .fontWeight(.semibold)
            }
            .foregroundColor(.white)
            .frame(width: UIScreen.main.bounds.width - 80, height: 48)
            .background(Color("AccentColor"))
            .cornerRadius(30)
            Spacer()
        }
        .padding(.horizontal, 20)
        .padding(.top, 20)

    }
}

struct PasswordRecovery_Previews: PreviewProvider {
    static var previews: some View {
        password_recovery()
    }
}

