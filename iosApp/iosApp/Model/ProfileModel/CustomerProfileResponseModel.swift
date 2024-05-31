//
//  CustomerProfileResponseModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 5/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct CustomerProfileResponse: Codable {
    var accounts: [Accounts]
    var addresses: [Addresses]
    var birthDate: String
    var customerId: String
    var customerType: String
    var fatherHusbandName: String
    var gender: String
    var lastName: String
    var motherName: String
    var nationalId: String
    var nationality: String
    var passportExpireDate: String
    var passportIssueDate: String
    var passportIssuePlace: String
    var passportNumber: String
    var surname: String
    var taxId: String
    var title: String
}
