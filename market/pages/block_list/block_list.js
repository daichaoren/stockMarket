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
    //排行榜数据        
    var url = getApp().globalData.basicUrl + '/index/block/top/' + page.data.sortType + '?pageSize=' + page.data.pageSize
      + '&pageNum=' + pageNum;   
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

  //点击板块
  clickBlock: function(event){
    wx.navigateTo({
      url: '../block/block?blockName=' + event.currentTarget.dataset.blockName 
      + '&blockCode=' + event.currentTarget.dataset.blockCode,
    });
    
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