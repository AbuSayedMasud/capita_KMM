//
//  accountModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 19/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

// Extend AccountBalance to conform to Decodable
struct iOSAccountBalance: Codable {
    let accountCode: String
    let accruedCharge: Double
    let assetValue: Double
    let buyingPower: Double
    let cashBalance: Double
    let costValue: Double
    let currentBalance: Double
    let deptEquityRatio: Double
    let equity: Double
    let equityDebtRatio: Double
    let immatureBalance: Double
    let loanRatio: Double
    let marginEquity: Double
    let marketValue: Double
    let totalDeposit: Double
    let totalWithdrawal: Double
    let unclearCheque: Double
    
    // Initialize iOSAccountBalance from shared AccountBalance
    init(sharedBalance: AccountBalance) {
        self.accountCode = sharedBalance.accountCode
        self.accruedCharge = sharedBalance.accruedCharge
        self.assetValue = sharedBalance.assetValue
        self.buyingPower = sharedBalance.buyingPower
        self.cashBalance = sharedBalance.cashBalance
        self.costValue = sharedBalance.costValue
        self.currentBalance = sharedBalance.currentBalance
        self.deptEquityRatio = sharedBalance.deptEquityRatio
        self.equity = sharedBalance.equity
        self.equityDebtRatio = sharedBalance.equityDebtRatio
        self.immatureBalance = sharedBalance.immatureBalance
        self.loanRatio = sharedBalance.loanRatio
        self.marginEquity = sharedBalance.marginEquity
        self.marketValue = sharedBalance.marketValue
        self.totalDeposit = sharedBalance.totalDeposit
        self.totalWithdrawal = sharedBalance.totalWithdrawal
        self.unclearCheque = sharedBalance.unclearCheque
    }
}
