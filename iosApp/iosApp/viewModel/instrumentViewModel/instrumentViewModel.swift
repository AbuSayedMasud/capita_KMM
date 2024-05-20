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
    /*
     func fetchInstruments() {
     let homeInstrument = accountInstrument.getInstrumentServices()
     //print(homeInstrument)
     guard let data = homeInstrument.data(using: .utf8) else {
     self.instrumentsList = nil
     return
     }
     
     do {
     let decodedData = try JSONDecoder().decode(AccountInstrument.self, from: data)
     self.instrumentsList = decodedData
     print(instrumentsList as Any)
     } catch {
     print("Error decoding instrument data: \(error)")
     self.instrumentsList = nil
     }
     }
     */
    
    //    func fetchInstruments() {
    //        let homeInstrument = accountInstrument.getInstrumentServices()
    //        print(homeInstrument)
    //        if let data = homeInstrument.data(using: .utf8) {
    //            do {
    //                let jsonObject = try JSONSerialization.jsonObject(with: data, options: [])
    //                if let jsonDict = jsonObject as? [String: Any],
    //                   let instrumentsData = jsonDict["instruments"] as? [[String: Any]] {
    //                    // Iterate over the array of instrument dictionaries
    //                    print(instrumentsData)
    //                    for instrumentDict in instrumentsData {
    //                        // Extract instrument information as needed
    //                        if let symbole = instrumentDict["symbole"] as? String {
    //                            print("Symbole: \(symbole)")
    //                            self.symbole = symbole
    //                        }
    //                        if let costPrice = instrumentDict["costPrice"] as? Double {
    //                            print("Cost Price: \(costPrice)")
    //                            self.costPrice = costPrice
    //                        }
    //                        //                        if let costValue = instrumentDict["costValue"] as? Double {
    //                        //                            print("Cost Value: \(costValue)")
    //                        //                        }
    //                        //                        if let gr = instrumentDict["gr"] as? String {
    //                        //                            print("GR: \(gr)")
    //                        //                        }
    //                        //                        if let marginable = instrumentDict["marginable"] as? Int {
    //                        //                            print("Marginable: \(marginable)")
    //                        //                        }
    //                        if let marketPrice = instrumentDict["marketPrice"] as? Double
    //                        {
    //                            print("Market Price: \(marketPrice)")
    //                            self.marketPrice = marketPrice
    //                        }
    //                        //                        if let marketValue = instrumentDict["marketValue"] as? Double {
    //                        //                            print("Market Value: \(marketValue)")
    //                        //                        }
    //                        //                        if let matureQuantity = instrumentDict["matureQuantity"] as? Int {
    //                        //                            print("Mature Quantity: \(matureQuantity)")
    //                        //                        }
    //                        //                        if let quantity = instrumentDict["quantity"] as? Int {
    //                        //                            print("Quantity: \(quantity)")
    //                        //                        }
    //                        //                        if let unrealizedGainString = instrumentDict["unrealizedGain"] as? String,
    //                        //                           let unrealizedGain = Double(unrealizedGainString) {
    //                        //                            print("Unrealized Gain: \(unrealizedGain)")
    //                        //                        }
    //
    //                    }
    //                }
    //            } catch {
    //                print("Error parsing instrument JSON: \(error)")
    //            }
    //        }
    func fetchInstruments() {
        let homeInstrument = accountInstrument.getInstrumentServices()
        if let data = homeInstrument.data(using: .utf8) {
            do {
                let jsonObject = try JSONSerialization.jsonObject(with: data, options: [])
                if let jsonDict = jsonObject as? [String: Any],
                   let instrumentsData = jsonDict["instruments"] as? [[String: Any]] {
                    DispatchQueue.main.async {
                        self.instrumentsList = instrumentsData
                    }
                }
            } catch {
                print("Error parsing instrument JSON: \(error)")
            }
        }
    
    }
}
