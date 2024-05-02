////
////  profileViewModel.swift
////  iosApp
////
////  Created by LEADS Corporation Limited on 1/5/24.
////  Copyright © 2024 orgName. All rights reserved.
////
//
//// ProfileViewModel.swift
//import Foundation
////import shared
//import shared
//
//class ProfileViewModel: ObservableObject {
//    @Published var profileData: String = ""
//    private let repository: CustomerProfileRepository
//    
//    let profileService = CustomerProfileServiceImpl(databaseDriverFactory)
//    // Fetch the account balance information from the service
//    let profile = profileService.getCustomerProfile()
//    
//    init(repository: CustomerProfileRepository) {
//        self.repository = repository
//    }
//
//    func fetchProfileData() {
//        // Call the repository method to fetch profile data
//        profileData = repository.getCustomerProfile()
//    }
//    
//    
//    private let customerProfileService: CustomerProfileService
//    
//    init(identityService: IdentityService, customerProfileService: CustomerProfileService) {
//        //self.identityService = identityService
//        self.customerProfileService = customerProfileService
//    }
//    
//    // Method to fetch profile information
//    func fetchProfile() {
//        let profile = customerProfileService.getCustomerProfile()
//        // Process the profile data as needed
//    }
//}
//
