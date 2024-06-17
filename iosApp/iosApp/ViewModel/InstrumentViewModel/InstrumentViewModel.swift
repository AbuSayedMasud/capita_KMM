//
//  instrumentViewModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class InstrumentViewModel: ObservableObject {
    
    //Published var instrumentsList: AccountInstrument?
    //@Published var instrumentsList: [Instrument] = []
    @Published var instrumentsList: [[String: Any]] = []
    
    @Published var symbole: String = ""
    @Published var marketPrice: Double = 0.0
    @Published var costPrice: Double = 0.0
    
    
    let databaseDriverFactory = DatabaseDriverFactory()
    var accountInstrument: AccountService
    
    init() {
        self.accountInstrument = AccountServiceImpl(databaseDriverFactory: databaseDriverFactory)
        fetchInstruments()
    }
    
    
    func fetchInstruments() {
        let homeInstrument = accountInstrument.getInstrumentServices()
        if let data = homeInstrument.data(using: .utf8) {
            do {
                let jsonObject = try JSONSerialization.jsonObject(with: data, options: [])
                if let jsonArray = jsonObject as? [[String: Any]] {
                    DispatchQueue.main.async {
                        self.instrumentsList = jsonArray
                        print(self.instrumentsList)
                    }
                }
            } catch {
                print("Error parsing instrument JSON: \(error)")
            }
        }
    }
    //    func fetchInstruments() {
    //        let homeInstrument = accountInstrument.getInstrumentServices()
    //        if let data = homeInstrument.data(using: .utf8) {
    //            do {
    //                let jsonObject = try JSONSerialization.jsonObject(with: data, options: [])
    //
    //                if let jsonDict = jsonObject as? [String: Any],
    //                   let instrumentsData = jsonDict["instruments"] as? [[String: Any]] {
    //                    DispatchQueue.main.async {
    //                        self.instrumentsList = instrumentsData
    //                        print(self.instrumentsList)
    //                    }
    //                }
    //            } catch {
    //                print("Error parsing instrument JSON: \(error)")
    //            }
    //        }
    //
    //    }
}
