//
//  instrumentModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import Foundation

struct iOSAccountAccountInstrument: Codable {
    //let accountCode: String
    let instruments: [Instrument]
}

struct Instrument: Codable {
    let costPrice: Double?
    let costValue: Double?
    let gr: String?
    let marginable: Bool?
    let marketPrice: Double?
    let marketValue: Double?
    let matureQuantity: Int?
    let quantity: Int?
    let symbole: String?
    let unrealizedGain: Double?
}

//// Extend AccountInstrument to conform to Decodable
//struct iOSAccountInstrument: Codable {
//    let accountCode: String
//    let instruments: [iOSInstrument]
//}
//
//// Extend Instrument to conform to Decodable
//struct iOSInstrument: Codable {
//    let costPrice: Double?
//    let costValue: Double?
//    let gr: String?
//    let marginable: Bool?
//    let marketPrice: Double?
//    let marketValue: Double?
//    let matureQuantity: Int?
//    let quantity: Int?
//    let symbole: String?
//    let unrealizedGain: Double?
//}
//
//// Initialize iOSAccountInstrument from shared AccountInstrument
//extension iOSAccountInstrument {
//    init(sharedInstrument: Instrument) {
//        self.accountCode = sharedInstrument.accountCode!
//        self.instruments = sharedInstrument.instrument.map { sharedInstrument in
//            return iOSInstrument(
//                costPrice: sharedInstrument.costPrice as? Double,
//                costValue: sharedInstrument.costValue as? Double,
//                gr: sharedInstrument.gr,
//                marginable: sharedInstrument.marginable as? Bool,
//                marketPrice: sharedInstrument.marketPrice as? Double,
//                marketValue: sharedInstrument.marketValue as? Double,
//                matureQuantity: sharedInstrument.matureQuantity as? Int,
//                quantity: sharedInstrument.quantity as? Int,
//                symbole: sharedInstrument.symbole,
//                unrealizedGain: sharedInstrument.unrealizedGain as? Double
//            )
//        }
//    }
//}
