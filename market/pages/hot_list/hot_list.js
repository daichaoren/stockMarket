// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {    
    pageSize: 10,
    pageNum: 1,  
    pageTitle: "涨幅排行榜",    
    sortType: 'increase',
    StockMarketArray: [],
    loadMore: "加载更多>"
  },

  //加载页面数据
  laodPage: function(pageNum){
    var page = this;
    var result = page.data.StockMarketArray;    
    var url;
    //排行榜数据
    if (page.data.sortType=="increase"){      
      url = getApp().globalData.basicUrl + '/index/stock/top/ChangePercent?pageSize=' + page.data.pageSize
        + '&pageNum=' + pageNum;
    } else if (page.data.sortType == "fall"){
      url = getApp().globalData.basicUrl + '/index/stock/top/ChangePercent?sortType=1&pageSize=' + page.data.pageSize
        + '&pageNum=' + pageNum;
    } else if (page.data.sortType == "turnOver") {
      url = getApp().globalData.basicUrl + '/index/stock/top/TurnoverRate?pageSize=' + page.data.pageSize
        + '&pageNum=' + pageNum;
    }else{
      return;
    }
    //获取涨幅榜数据
    wx.request({
      url: url,
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          page.setData({
            loadMore: "加载完毕"
          });
          return;
        }
        var cont = result.concat(res.data.data);
        page.setData({
          StockMarketArray: cont,                   
          pageNum: pageNum + 1,
         
        });
      }
    });
  }, 
  //滚动刷新
  loadMore: function(){  
    if (this.data.loadMore=="加载完毕"){
      wx.showToast({
        title: '没有更多数据了',
        icon: 'succes',
        duration: 1000,
        mask: true
      });
      return;
    }
    this.laodPage(this.data.pageNum);
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      pageTitle: options.pageTitle,      
      sortType: options.sortType
    });    
    this.laodPage(1);
  },
  //跳转具体股票页面
  gotoStockDetail: function (event) {
    var stockName = event.currentTarget.dataset.stockName;
    var stockCode, stockSinaCode;
    switch (stockName) {
      case "上证指数":
        stockCode = "000001";
        stockSinaCode = "sh000001";
        break;
      case "深证成指":
        stockCode = "399001";
        stockSinaCode = "sz399001";
        break;
      case "创业板指":
        stockCode = "399006";
        stockSinaCode = "sz399006";
        break;
      default:
        stockCode = event.currentTarget.dataset.stockCode;
        stockSinaCode = this.getSinaCode(stockCode);
        break;

    }
    wx.navigateTo({
      url: '../stock/stock?name=' + stockName + '&code=' + stockCode + '&sinaCode=' + stockSinaCode,
    });
  },
  //获取新浪编码
  getSinaCode: function (code) {
    switch (code.charAt(0)) {
      case "6":
      case "9":
        return "sh" + code;
      default:
        return "sz" + code;
    }
  },
  
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //点击搜搜
  clickSearch: function () {
    wx.navigateTo({
      url: '../search/search',
    });
  }
})