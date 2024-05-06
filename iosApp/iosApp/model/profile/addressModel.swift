//
//  addressModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 5/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct Addresses: Codable {
    var address1: String
    var address2: String
    var address3: String
    var addressType: String
    var city: String
    var contactPerson: String
    var country: String
    var email: String
    var mobileNumber: String
    var phoneNumber: String
    var state: String
    var zipCode: String
}
