//
//  LoginView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 4/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import LocalAuthentication
import SwiftUI
import shared

struct LoginView: View {
    @State private var username = ""
    @State private var password = ""
    
    @State private var rememberMe = false
    @State var isShowingPassword: Bool = false
    @State private var isLogged = false
    @State private var navigateToHome = false
    
    // @StateObject var viewModel: LoginViewModel
    
    @State private var isActive = false
    //@State private var showErrorAlert = false
    @State private var showAlert = false
    @State private var alertMessage = ""
    @State private var isLoggedIn = false // Added state variable to track login status
    
    var body: some View {
        
        NavigationStack{
            VStack{
                Spacer()
                //app name
                Image("Capita")
                    .resizable()
                    .frame(width: 123, height: 46)
                    .foregroundColor(.primary)
                //Spacer()
                //form
                
                // Username TextField
                VStack(alignment: .leading) {
                    Text("Username")
                    TextField("Enter your Username", text: $username)
                    
                        .padding(.vertical, 10)
                        .padding(.horizontal, 10)
                        .overlay(
                            RoundedRectangle(cornerRadius: 5)
                                .stroke(Color.primary, lineWidth: 1)
                        )
                }
                .padding(.horizontal)
                
                // Password SecureField
                
                VStack(alignment: .leading) {
                    Text("Password")
                    HStack {
                        Group {
                            if isShowingPassword {
                                TextField("Enter your password", text: $password)
                            } else {
                                SecureField("Enter your password", text: $password)
                            }
                        }
                        .disableAutocorrection(true)
                        .autocapitalization(.none)
                        .padding(.vertical, 10)
                        .padding(.horizontal, 10)
                        .overlay(alignment: .leading){
                            RoundedRectangle(cornerRadius: 5)
                                .stroke(Color.primary, lineWidth: 1)
                            
                            Button(action: {
                                isShowingPassword.toggle()
                            }) {
                                Image(systemName: isShowingPassword ? "eye.slash.fill" : "eye.fill")
                                    .foregroundColor(.primary)
                            }
                            .padding(.leading, 280)
                            
                        }
                        
                        
                    }
                }
                .padding(.horizontal)
                
                //remember & forget button
                HStack{
                    HStack{
                        Image(systemName: rememberMe ? "checkmark.square.fill" : "square") // Use system icons for checkbox appearance
                            .resizable()
                            .frame(width: 20, height: 20)
                            .foregroundColor(.primary)
                            .onTapGesture {
                                rememberMe.toggle() // Toggle the state variable on tap
                            }
                        Text("Remember me")
                            .fontWeight(.light)
                            .font(.system(size: 14))
                        //.padding(.leading, -5)
                    }
                    .padding(.leading, 20)
                    Spacer()
                    NavigationLink {
                        password_recovery()
                            .navigationTitle("Password Recovery")
                        
                    } label: {
                        HStack{
                            Text("Forget Password?")
                                .fontWeight(.medium)
                                .underline()
                                .foregroundColor(Color("AccentColor"))
                        }
                        .font(.system(size: 14))
                    }
                    .padding(.top,10)
                    .padding(.trailing,20)
                }
                
                //LOGIN button
                Button(action: {
                    LoginManager().login(username: username, password: password) { result in
                        switch result {
                        case .success:
                            print("Login success")
                            // Set isLoggedIn to true upon successful login
                            isLoggedIn = true
                        case .invalid(let errorMessage):
                            print("Login failed: \(errorMessage)")
                            // Handle failed login
                            // Show alert with error message
                            showAlert = true
                            alertMessage = errorMessage.joined(separator: "\n")
                        }
                    }
                    
                }) {
                    Text("Login")
                        .fontWeight(.semibold)
                        .foregroundColor(.white)
                        .frame(width: UIScreen.main.bounds.width - 100, height: 48)
                        .background(Color("AccentColor"))
                        .cornerRadius(30)
                        .padding(.top, 35)
                    
                }
                
                //SIGN-UP BUTTON
                NavigationLink {
                    RegistrationView()
                        .navigationTitle("Registration")
                    
                } label: {
                    HStack{
                        Text("I don't have an account?")
                            .foregroundColor(Color.primary)
                        Text("Sign Up")
                            .fontWeight(.medium)
                            .underline()
                            .foregroundColor(Color("AccentColor"))
                    }
                    .font(.system(size: 14))
                }
                .padding(.top,10)
                
                
                
                //FACE OR TOUCH ID image
                Image("face_id") // Display Face ID or Touch ID image
                    .resizable()
                    .frame(width: 60, height: 60)
                    .foregroundColor(.primary) // Set the image color if needed
                    .padding(.top,30)
                
                //ADDING FACE OR TOUCHID
                NavigationLink(destination: BiometricAuthView {
                    // Closure to handle successful biometric authentication
                    print("Biometric authentication successful")
                    // Proceed with login or any action after successful authentication
                }) {
                    HStack{
                        Text("Don't have TouchID yet?")
                            .foregroundColor(Color.primary)
                        Text("TouchID")
                            .fontWeight(.medium)
                            .underline()
                            .foregroundColor(Color("AccentColor"))
                    }
                    .font(.system(size: 14))
                }
                .padding(.top,10)
                Spacer()
                //shanta-image
                HStack{
                    Image("ShantaLogo")
                        .resizable()
                        .frame(width: 60, height: 46)
                        .foregroundColor(.primary)
                    
                    Text("SHANTA")
                        .fontWeight(.medium)
                        .font(.title)
                        .foregroundColor(Color("AccentColor"))
                }
                
                Spacer()
            }
            .alert(isPresented: $showAlert) {
                Alert(title: Text("Error"), message: Text(alertMessage), dismissButton: .default(Text("OK")))
            }
            // Navigate to HomeView when isLoggedIn is true
            .background(
                NavigationLink(
                    destination: HomeView().navigationBarHidden(true),
                    isActive: $isLoggedIn,
                    label: EmptyView.init
                )
            )
        }
        .topSafeAreaColor()
    }
    
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        // LoginView(viewModel: <#T##LoginViewModel#>)
        //LoginView(viewModel: LoginViewModel(identityService: IdentityServiceImpl()))
        
        LoginView()
    }
}
