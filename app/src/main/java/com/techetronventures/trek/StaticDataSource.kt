package com.techetronventures.trek

object StaticDataSource {

    fun getAllOnBoardList(): List<OnBoardItemModel> {
        val dataList = mutableListOf<OnBoardItemModel>()

        dataList.add(
            OnBoardItemModel(
                R.raw.welcome_to_trek,
                R.string.onboarding_title_1,
                R.string.onboarding_description_1
            )
        )
        dataList.add(
            OnBoardItemModel(
                R.raw.mobile_payment_in_hand,
                R.string.onboarding_title_2,
                R.string.onboarding_description_2
            )
        )
        dataList.add(
            OnBoardItemModel(
                R.raw.everything_at_your_fingertips,
                R.string.onboarding_title_3,
                R.string.onboarding_description_3
            )
        )
        dataList.add(
            OnBoardItemModel(
                R.raw.easy_bo_account_access_1,
                R.string.onboarding_title_5,
                R.string.onboarding_description_5
            )
        )
        dataList.add(
            OnBoardItemModel(
                R.raw.learn_invest_grow,
                R.string.onboarding_title_6,
                R.string.onboarding_description_6
            )
        )
        dataList.add(
            OnBoardItemModel(
                R.raw.account_protection_trade_line,
                R.string.onboarding_title_7,
                R.string.onboarding_description_7
            )
        )
        return dataList
    }
}