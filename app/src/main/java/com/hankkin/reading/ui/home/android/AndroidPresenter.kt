package com.hankkin.reading.ui.home.android

import com.hankkin.reading.http.HttpClient
import com.hankkin.reading.mvp.presenter.RxLifePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by huanghaijie on 2018/7/8.
 */
class AndroidPresenter : RxLifePresenter<AndroidContact.IView>(), AndroidContact.IPresenter {
    override fun cancelCollectHttp(id: Int) {
        HttpClient.Builder.getCommonHttp()
                .cancelCollectArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().cancelCollectResult(id)
                },{
                    getMvpView().setFail()
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun collectHttp(id: Int) {
        HttpClient.Builder.getCommonHttp()
                .collectArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().collectResult(id)
                },{
                    getMvpView().setFail()
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun getArticle(page: Int) {
        HttpClient.Builder.getCommonHttp()
                .getArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().setArticle(it.data)
                },{
                    getMvpView().setFail()
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }


}