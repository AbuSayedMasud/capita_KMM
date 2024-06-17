//
//  IOSMockLoader.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 19/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IOSMockLoader {
    
    let service: MockService
    
    init() {
        // Initialize the MockService with the appropriate DatabaseDriverFactory
        let databaseDriverFactory = DatabaseDriverFactory()
        self.service = MockService(databaseDriverFactory: databaseDriverFactory)
        
        // Call the init() method to load the JSON data
        self.initData()
    }
    
    // Function to load JSON data and pass it to the MockService
    private func initData() {
        // Load JSON data for account balance
        if let jsonString = loadJSON(fileName: "accountbalance") {
            parseAndLoadAccountBalances(jsonString: jsonString)
        } else {
            print("Failed to load JSON file.")
        }
        
        // Add similar code for other data types if needed
        if let instrumentsJSON = loadJSON(fileName: "accountinstruments") {
            parseAndLoadInstruments(jsonString: instrumentsJSON)
            print("file found account instrument")
        } else {
            print("Failed to load other entity JSON file.")
        }
    }
    
    // Function to load JSON from file
    private func loadJSON(fileName: String) -> String? {
        if let path = Bundle.main.path(forResource: fileName, ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                return String(data: data, encoding: .utf8)
            } catch {
                print("Error reading JSON file: \(error)")
            }
        }
        return nil
    }
    
    // Function to parse JSON into shared AccountBalance objects and load them
    private func parseAndLoadAccountBalances(jsonString: String) {
        print("Parsing account balances data...")
        
        guard let jsonData = jsonString.data(using: .utf8) else {
            print("Failed to convert JSON string to data.")
            return
        }
        
        do {
            let decoder = JSONDecoder()
            let balances = try decoder.decode([iOSAccountBalance].self, from: jsonData)
            
            // Convert iOSAccountBalance objects to AccountBalance objects
            let accountBalances = balances.map { iosBalance in
                return AccountBalance(
                    accountCode: iosBalance.accountCode,
                    accruedCharge: iosBalance.accruedCharge,
                    assetValue: iosBalance.assetValue,
                    buyingPower: iosBalance.buyingPower,
                    cashBalance: iosBalance.cashBalance,
                    costValue: iosBalance.costValue,
                    currentBalance: iosBalance.currentBalance,
                    deposit: iosBalance.deposit, deptEquityRatio: iosBalance.deptEquityRatio,
                    dividendIncome: iosBalance.equity,
                    dividendReceivable: iosBalance.equityDebtRatio,
                    equity: iosBalance.immatureBalance,
                    equityDebtRatio: iosBalance.loanRatio,
                    fundTransIn: iosBalance.marginEquity,
                    fundTransOut: iosBalance.marketValue,
                    fundWithdrawalRequest: iosBalance.totalDeposit,
                    immatureBalance: iosBalance.totalWithdrawal,
                    instrumentTransIn: iosBalance.unclearCheque,
                    instrumentTransOut: iosBalance.dividendIncome,
                    ipoApplication: iosBalance.dividendReceivable,
                    loanRatio: iosBalance.fundTransIn,
                    marginEquity: iosBalance.fundTransOut,
                    marketValue: iosBalance.fundWithdrawalRequest,
                    maxLoan: iosBalance.instrumentTransIn,
                    netDepositWithdraw: iosBalance.instrumentTransOut,
                    netGainLoss: iosBalance.ipoApplication,
                    preIpoApplication: iosBalance.maxLoan,
                    preferenceApplication: iosBalance.netDepositWithdraw,
                    realiseGainLoss: iosBalance.netGainLoss,
                    rightApplication: iosBalance.preIpoApplication,
                    rightOrder: iosBalance.preferenceApplication,
                    totalDeposit: iosBalance.realiseGainLoss,
                    totalWithdrawal: iosBalance.rightApplication,
                    unclearCheque: iosBalance.rightOrder,
                    unrealiseGainLoss: iosBalance.unrealiseGainLoss,
                    withdrawal: iosBalance.withdrawal
                )
            }
            
            // Call the service to load account balances
            service.loadAccountBalance(balances: accountBalances)
        } catch {
            print("Error decoding JSON: \(error)")
        }
    }
    
    // Function to parse JSON into shared Instrument objects and load them
    private func parseAndLoadInstruments(jsonString: String) {
        print("Parsing instruments data...")
        
        guard let jsonData = jsonString.data(using: .utf8) else {
            print("Failed to convert JSON string to data.")
            return
        }
        do {
                   let decoder = JSONDecoder()
                   let accountInstrument = try decoder.decode(iOSAccountAccountInstrument.self, from: jsonData)
                   
                   // Convert iOSAccountInstrument object to shared AccountInstrument object
                   let sharedAccountInstrument = shared.AccountInstrument(
                       //accountCode: accountInstrument.accountCode,
                       instruments: accountInstrument.instruments.map { iosInstrument in
                           return shared.Instrument_(
                               costPrice: iosInstrument.costPrice as? KotlinDouble,
                               costValue: iosInstrument.costValue as? KotlinDouble,
                               gr: iosInstrument.gr,
                               marginable: iosInstrument.marginable as? KotlinBoolean,
                               marketPrice: iosInstrument.marketPrice as? KotlinDouble,
                               marketValue: iosInstrument.marketValue as? KotlinDouble,
                               matureQuantity: iosInstrument.matureQuantity as? KotlinLong,
                               quantity: iosInstrument.quantity as? KotlinInt,
                               symbole: iosInstrument.symbole,
                               unrealizedGain: iosInstrument.unrealizedGain as? KotlinDouble
                           )
                       }
                   )
                   
                   // Call the service to load the account instrument
            service.loadAccountInstrument(instruments: sharedAccountInstrument)
        } catch {
            print("Error decoding JSON: \(error)")
        }
    }
    
}


//import Foundation
//import shared
//
//class IOSMockLoader {
////    private var isLoaded = false
//    let service: MockService
////    private let context: Any
//
//    init() {
//        // Replace with actual DatabaseDriverFactory and MockService implementations
//        let databaseDriverFactory = DatabaseDriverFactory()
//        self.service = MockService(databaseDriverFactory: databaseDriverFactory)
//        initData()
//    }
//    func initData() {
////        if isLoaded {
////            return
////        }
//
//
//
//
//
//
//
//        // account Transaction
//        if let jsonTransactionContent = loadJson(fileName: "accounttransaction") {
//            let transactions: [AccountTransaction] = decodeJson(jsonTransactionContent)
//            service.loadAccountTransaction(transactions: transactions)
//        }
//
//        // account Balance
//        if let jsonBalanceContent = loadJson(fileName: "accountbalance") {
//            let balances: [shared.AccountBalance] = decodeJson(jsonBalanceContent)
//            service.loadAccountBalance(balances: balances)
//        }
//
//        // account instrument
//        if let jsonAccountInstrumentContent = loadJson(fileName: "accountinstruments") {
//            let instruments: shared.AccountInstrument = decodeJson(jsonAccountInstrumentContent)
//            service.loadAccountInstrument(instruments: instruments)
//        }
//
//        // account Receivable
//        if let jsonReceivableContent = loadJson(fileName: "accountreceivable") {
//            let receivables: [AccountReceivable] = decodeJson(jsonReceivableContent)
//            service.loadAccountReceivable(receivables: receivables)
//        }
//
//        // index
//        if let jsonIndexContent = loadJson(fileName: "index") {
//            let indices: [Ticker] = decodeJson(jsonIndexContent)
//            service.loadIndices(indices: indices)
//        }
//
//        // instrument
//        if let jsonInstrumentContent = loadJson(fileName: "instrument") {
//            let instrument: [Ticker] = decodeJson(jsonInstrumentContent)
//            service.loadInstrument(instrument: instrument)
//        }
//
//        // news
//        if let jsonNewsContent = loadJson(fileName: "news") {
//            let news: [News] = decodeJson(jsonNewsContent)
//            service.loadNews(news: news)
//        }
//
//        // participation
//        if let jsonParticipationContent = loadJson(fileName: "participationlist") {
//            let participation: [Participation] = decodeJson(jsonParticipationContent)
//            service.loadParticipation(participation: participation)
//        }
//
//        // status
//        if let jsonStatusContent = loadJson(fileName: "statuslist") {
//            let status: [Status] = decodeJson(jsonStatusContent)
//            service.loadStatus(status: status)
//        }
//
//        // volume
//        if let jsonVolumeContent = loadJson(fileName: "volumelist") {
//            let volume: [Ticker] = decodeJson(jsonVolumeContent)
//            service.loadVolume(volume)
//        }
//
//        //isLoaded = true
//    }
//
//    /**
//     * Loads JSON data from a file.
//     *
//     * @param fileName The name of the JSON file located in the app bundle.
//     * @return The JSON data as a string.
//     */
//    private func loadJson(fileName: String) -> String? {
//        if let path = Bundle.main.path(forResource: fileName, ofType: "json") {
//            do {
//                let jsonContent = try String(contentsOfFile: path, encoding: .utf8)
//                return jsonContent
//            } catch {
//                print("Error loading JSON file: \(fileName), error: \(error)")
//            }
//        }
//        return nil
//    }
//
//    /**
//     * Decodes JSON string into the specified type.
//     *
//     * @param json The JSON string to decode.
//     * @return The decoded object of the specified type.
//     */
//    private func decodeJson<T: Decodable>(_ json: String) -> T {
//        let data = Data(json.utf8)
//        do {
//            let decodedObject = try JSONDecoder().decode(T.self, from: data)
//            return decodedObject
//        } catch {
//            fatalError("Failed to decode JSON: \(error)")
//        }
//    }
//}
//
////// Mock implementations for DatabaseDriverFactory and MockService
////class DatabaseDriverFactory {
////    init(context: Any) {
////        // Initialization code
////    }
////}
//
////class MockService {
////    init(databaseDriverFactory: DatabaseDriverFactory) {
////        // Initialization code
////    }
////
////    func loadAccountTransaction(_ transactions: [AccountTransaction]) {}
////    func loadAccountBalance(_ balances: [AccountBalance]) {}
////    func loadAccountInstrument(_ instruments: AccountInstrument) {}
////    func loadAccountReceivable(_ receivables: [AccountReceivable]) {}
////    func loadIndices(_ indices: [Ticker]) {}
////    func loadInstrument(_ instrument: [Ticker]) {}
////    func loadNews(_ news: [News]) {}
////    func loadParticipation(_ participation: [Participation]) {}
////    func loadStatus(_ status: [Status]) {}
////    func loadVolume(_ volume: [Ticker]) {}
////}
////
////// Define your Codable types such as AccountTransaction, AccountBalance, etc.
/////
/////
//struct AccountTransaction: Codable {}
//struct AccountBalance: Codable {}
//struct AccountInstrument: Codable {}
//struct AccountReceivable: Codable {}
//struct Ticker: Codable {}
//struct News: Codable {}
//struct Participation: Codable {}
//struct Status: Codable {}

import Foundation
import shared

//extension AccountTransaction: Codable {}
//extension AccountBalance : Identifiable,Decodable {
//}

//class IOSMockLoader {
//    private var isLoaded = false
//    private let service: MockService
//
//    init() {
//        // Initialize MockService
//        let databaseDriverFactory = DatabaseDriverFactory()
//        self.service = MockService(databaseDriverFactory: databaseDriverFactory)
//    }
//
//    func initData() {
//        guard !isLoaded else {
//            return
//        }
//
////        // Load JSON files from shared module
////        if let jsonTransactionContent = loadJson(fileName: "account_transaction") {
////            let transactions: [AccountTransaction] = decodeJson(jsonTransactionContent)
////            service.loadAccountTransaction(transactions: transactions)
////        }
////
//
//
//        if let jsonBalanceContent = loadJson(fileName: "accountbalance") {
//            let balances: [AccountBalance] = decodeJson(jsonBalanceContent)
//            service.loadAccountBalance(balances: balances)
//        }
//
////        if let jsonAccountInstrumentContent = loadJson(fileName: "account_instruments") {
////            let instruments: AccountInstrument = decodeJson(jsonAccountInstrumentContent)
////            service.loadAccountInstrument(instruments: instruments)
////        }
////
////        if let jsonReceivableContent = loadJson(fileName: "account_receivable") {
////            let receivables: [AccountReceivable] = decodeJson(jsonReceivableContent)
////            service.loadAccountReceivable(receivables)
////        }
////
////        if let jsonIndexContent = loadJson(fileName: "index") {
////            let indices: [Ticker] = decodeJson(jsonIndexContent)
////            service.loadIndices(indices)
////        }
////
////        if let jsonInstrumentContent = loadJson(fileName: "instrument") {
////            let instrument: [Ticker] = decodeJson(jsonInstrumentContent)
////            service.loadInstrument(instrument)
////        }
////
////        if let jsonNewsContent = loadJson(fileName: "news") {
////            let news: [News] = decodeJson(jsonNewsContent)
////            service.loadNews(news)
////        }
////
////        if let jsonParticipationContent = loadJson(fileName: "participation_list") {
////            let participation: [Participation] = decodeJson(jsonParticipationContent)
////            service.loadParticipation(participation)
////        }
////
////        if let jsonStatusContent = loadJson(fileName: "status_list") {
////            let status: [Status] = decodeJson(jsonStatusContent)
////            service.loadStatus(status: status)
////        }
//
////        if let jsonVolumeContent = loadJson(fileName: "volume_list") {
////            let volume: [Ticker] = decodeJson(jsonVolumeContent)
////            service.loadVolume(volume)
////        }
//
//        isLoaded = true
//    }
//
//    /**
//     * Loads JSON data from shared module.
//     *
//     * @param fileName The name of the JSON file located in the shared module.
//     * @return The JSON data as a string.
//     */
//
//
//        private func loadJson(fileName: String) -> String? {
//            if let path = Bundle.main.path(forResource: fileName, ofType: "json") {
//                do {
//                    let jsonContent = try String(contentsOfFile: path, encoding: .utf8)
//                    return jsonContent
//                } catch {
//                    print("Error loading JSON file: \(fileName), error: \(error)")
//                }
//            }
//            return nil
//        }
//
//    /**
//     * Decodes JSON string into the specified type.
//     *
//     * @param json The JSON string to decode.
//     * @return The decoded object of the specified type.
//     */
//    private func decodeJson<T: Decodable>(_ json: String) -> T {
//        guard let data = json.data(using: .utf8) else {
//            fatalError("Failed to convert JSON string to data")
//        }
//        do {
//            return try JSONDecoder().decode(T.self, from: data)
//        } catch {
//            fatalError("Failed to decode JSON: \(error)")
//        }
//    }
//}
//
//

