//
//  loginViewModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 28/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class LoginViewModel: ObservableObject {
    @Published var isAuthenticated: Bool = false
    @Published var errorMessage: String?

    private let identityService: IdentityService
    
    init(identityService: IdentityService) {
        self.identityService = identityService
    }
    

    /*
    func login(username: String, password: String, isActive: Binding<Bool>) {
        // Call the authenticate method from the IdentityService
        let isAuthenticated = identityService.authenticate(username: username, password: password)
        
        print(isAuthenticated)
        // Check if the authentication was successful
        if isAuthenticated {
            // Update the authentication status
            self.isAuthenticated = true
            // Optionally, you can perform additional actions after successful login

            
            // Set the isActive binding to true to trigger NavigationLink
                  isActive.wrappedValue = true

        } else {
            // Handle authentication failure
            self.isAuthenticated = false
            self.errorMessage = "Authentication failed. Please check your username and password."
           // showAlert = true
            
        }
    }
    */
}

