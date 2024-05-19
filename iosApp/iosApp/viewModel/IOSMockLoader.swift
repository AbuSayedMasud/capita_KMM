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
         if let instrumentsJSON = loadJSON(fileName: "instrument") {
             //parseAndLoadinstruments(jsonString: instrumentsJSON)
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
    
  

}
