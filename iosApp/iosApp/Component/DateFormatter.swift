//
//  dateFormatter.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

var dateFormatter: DateFormatter {
     let formatter = DateFormatter()
     formatter.dateFormat = "MM/dd/yyyy"
     return formatter
 }

extension Date {
    func formatted(_ formatter: DateFormatter) -> String {
        formatter.string(from: self)
    }
}
