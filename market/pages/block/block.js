// pages/block/block.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageSize: 10,
    pageNum: 1,  
    totalPage: 1,
    blockCode: 'BK0433',
    blockName: '农牧饲渔',
    StockMarketArray: [],
    blockMarket: {}
  },
  //初始化板块行情
  initMarket: function(){
    var page=this;
    wx.request({
      url: getApp().globalData.basicUrl + 'index/block/market/' + page.data.blockCode,
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
        page.setData({
          blockMarket: res.data.data
        });
        console.log(res.data);
      }
    });
  },
  //加载页面数据
  laodPage: function (pageNum) {
    var page = this;
    var result = page.data.StockMarketArray;
    //获取涨幅榜数据
    wx.request({
      url: getApp().globalData.basicUrl + 'index/block/stocks/' + page.data.blockCode +'?pageSize=' 
      + page.data.pageSize + '&pageNum=' + pageNum,
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
        
        if (res.data.totalPage <= pageNum+1){          
          page.setData({
            loadMore: "加载完毕"
          });
        }
        var cont = result.concat(res.data.data);
        page.setData({
          StockMarketArray: cont,          
          pageNum: pageNum + 1,
          totalPage: res.data.totalPage
          
        });        
      }
    });
  }, 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      blockCode: options.blockCode,
      blockName: options.blockName
    });
    //初始化行情
    this.initMarket();
    this.laodPage(1);
  },
  //滚动刷新
  loadMore: function () {    
    if (this.data.loadMore == "加载完毕") {
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