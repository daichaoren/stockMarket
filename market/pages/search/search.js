// pages/search/search.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchResult: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  //跳转具体股票页面
  gotoStockDetail: function (event) {
    var stockName = event.currentTarget.dataset.stockName;   
    var stockCode = event.currentTarget.dataset.stockCode;
    var stockSinaCode = event.currentTarget.dataset.stockSinaCode;    
    wx.navigateTo({
      url: '../stock/stock?name=' + stockName + '&code=' + stockCode + '&sinaCode=' + stockSinaCode,
    });
  },
  watchSeach: function(event){   
    var page = this; 
    wx.request({
      url: getApp().globalData.basicUrl + '/index/search/stocks/' + event.detail.value,
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if (res.data.state != 0) {
          wx.showToast({
            title: '查询失败',
          });
          return;
        }
        page.setData({
          searchResult: res.data.data
        });

      }
    });  
  }
})