//
//  profileViewModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 1/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

// ProfileViewModel.swift

import Foundation
import shared

class ProfileViewModel: ObservableObject {

    @Published var profileData: CustomerProfileResponse?
    @Published var accountCodes = "..."
    @Published var userName = "..."
    @Published var boId = "..."
    @Published var popupTitles = [String]() // Added property for popupTitles
    
    private let profileService: CustomerProfileService
    
    init(profileService: CustomerProfileService) {
        self.profileService = profileService
        fetchProfileData()
    }
    
    func fetchProfileData() {
        // Get the profile data from the service
        let jsonString = profileService.getCustomerProfile()
        
        // Check if the returned JSON string is not empty
        if !jsonString.isEmpty {
            // Attempt to decode JSON string into CustomerProfileResponse object
            if let profile = try? JSONDecoder().decode(CustomerProfileResponse.self, from: jsonString.data(using: .utf8)!) {
                // Successfully decoded JSON string into CustomerProfileResponse
                self.profileData = profile
                print(profileData as Any)
                // Extract relevant data and assign to corresponding properties
                if let accounts = profile.accounts.first {
                    self.accountCodes = accounts.accountCode
                    self.boId = accounts.boId
                   
                }
                self.userName = "\(profile.lastName)"
                self.popupTitles = profile.accounts.map { $0.accountCode }
            } else {
                // Handle error when unable to decode JSON string
                self.profileData = nil
            }
        } else {
            // Handle error when the profile data is empty
            self.profileData = nil
        }
    }


}

