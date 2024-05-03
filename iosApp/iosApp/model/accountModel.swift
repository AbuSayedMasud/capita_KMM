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
    let deposit: Double
    let dividendIncome: Double
    let dividendReceivable: Double
    let fundTransIn: Double
    let fundTransOut: Double
    let fundWithdrawalRequest: Double
    let instrumentTransIn: Double
    let instrumentTransOut: Double
    let ipoApplication: Double
    let maxLoan: Double
    let netDepositWithdraw: Double
    let netGainLoss: Double
    let preIpoApplication: Double
    let preferenceApplication: Double
    let realiseGainLoss: Double
    let rightApplication: Double
    let rightOrder: Double
    let unrealiseGainLoss: Double
    let withdrawal: Double
    
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
        
        // Assign default values for new fields
        self.deposit = sharedBalance.deposit
        self.dividendIncome = sharedBalance.dividendIncome
        self.dividendReceivable = sharedBalance.dividendReceivable
        self.fundTransIn = sharedBalance.fundTransIn
        self.fundTransOut = sharedBalance.fundTransOut
        self.fundWithdrawalRequest = sharedBalance.fundWithdrawalRequest
        self.instrumentTransIn = sharedBalance.instrumentTransIn
        self.instrumentTransOut = sharedBalance.instrumentTransOut
        self.ipoApplication = sharedBalance.ipoApplication
        self.maxLoan = sharedBalance.maxLoan
        self.netDepositWithdraw = sharedBalance.netDepositWithdraw
        self.netGainLoss = sharedBalance.netGainLoss
        self.preIpoApplication = sharedBalance.preIpoApplication
        self.preferenceApplication = sharedBalance.preferenceApplication
        self.realiseGainLoss = sharedBalance.realiseGainLoss
        self.rightApplication = sharedBalance.rightApplication
        self.rightOrder = sharedBalance.rightOrder
        self.unrealiseGainLoss = sharedBalance.unrealiseGainLoss
        self.withdrawal = sharedBalance.withdrawal
    }
}
