//index.js
//获取应用实例
const app = getApp()

Page({
  data:{
    //tab选择页变量
    tab_seleted: {
      market_selected:true,
      select_selected: false
    },
    //自选排序选项
    select_sort: {
      current_price_down: true,
      current_price_up: false,
      price_percent_down: false,
      price_percent_up: false,
      price_increase_down: false,
      price_increase_up: false,
    },
    //板块大类
    blockClass: [
      {
        name: "行业板块",
        code: "C._BKHY",
        blockList: []
        },
      {
        name: "概念板块",
        code: "C._BKGN",
        blockList: []
      },
      {
        name: "地区板块",
        code: "C._BKDY",
        blockList: []
      }
    ],
    //排行榜选择
    hot_tab_selected: {
      increase: true,
      fall: false,
      turnOver: false
    },
    //自选股列表
    selectedStocks: []
  }, 
  //初始化页面
  initPage: function(){
    var page = this;

    //设置三大指数值
    wx.request({
      url: getApp().globalData.basicUrl + '/index/market/index',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //console.log(res.data);
        //获取数据失败
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          return;
        }

        page.setData({
          indexMarketArray: res.data.data,
        });
      }
    });

    //获取三大板块类数据
    for (var i = 0; i < page.data.blockClass.length; i++) {
      //发送请求
      wx.request({
        url: getApp().globalData.basicUrl + '/index/block/top/' + page.data.blockClass[i].code,
        header: {
          'content-type': 'application/json' // 默认值
        },
        success(res) {
          //console.log(res);
          //获取数据失败
          if (res.data.state != 0) {
            console.log(res.data.errorMessage);
            return;
          }
          var iIndex = -1;
          for (var j = 0; j < page.data.blockClass.length; j++) {
            //成功返回，将errorMessage中放入code
            if (page.data.blockClass[j].code == res.data.errorMessage) {
              iIndex = j;
            }
          }
          if (iIndex == -1) return;
          var temp_str = 'blockClass[' + iIndex + '].blockList';
          page.setData({
            [temp_str]: res.data.data
          });
        }
      });
    }

    //获取涨幅榜数据
    wx.request({
      url: getApp().globalData.basicUrl + '/index/stock/top/ChangePercent',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //console.log(res.data);
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          return;
        }
        page.setData({
          hotStockArray: res.data.data,
        });
      }
    });
  },
  //页面加载
  onLoad: function (options){
    this.initPage();   
  },
  //下拉刷新
  onPullDownRefresh: function () {
    this.initPage();   
    wx.stopPullDownRefresh();
  },
  //选择行情tab
  market_tab_tap: function(){
    this.setData({
      tab_seleted: {
        market_selected: true,
        select_selected: false
      }
    });
  },
  //选择自选tab
  select_tab_tap: function(){
    var page  = this;
    this.setData({
      tab_seleted: {
        market_selected: false,
        select_selected: true
      }
    });

    //获取自选行情
    wx.request({
      url: getApp().globalData.basicUrl + '/user/stocks?userId=' + wx.getStorageSync('user_id'),
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res){
        if (res.data.state != 0){
          console.log(res.data.errorMessage);
          return;
        }
        page.setData({
          selectedStocks: res.data.data
        });
      }
    })
  },

  //点击涨幅榜
  clickHotIncreaseTop: function(){
    var page = this;
    //获取涨幅榜数据
    wx.request({
      url: getApp().globalData.basicUrl + '/index/stock/top/ChangePercent',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {       
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          return;
        }
        page.setData({
          hotStockArray: res.data.data,
          hot_tab_selected: {
            increase: true,
            fall: false,
            turnOver: false
          }
        });
      }
    });
  },

  //点击跌幅榜
  clickHotFallTop: function () {
    var page = this;
    //获取涨幅榜数据
    wx.request({
      url: getApp().globalData.basicUrl + '/index/stock/top/ChangePercent?sortType=1',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          return;
        }
        page.setData({
          hotStockArray: res.data.data,
          hot_tab_selected: {
            increase: false,
            fall: true,
            turnOver: false
          }
        });
      }
    });
  },

  //点击换手率榜
  clickHotTurnOverTop: function () {
    var page = this;
    //获取涨幅榜数据
    wx.request({
      url: getApp().globalData.basicUrl + '/index/stock/top/TurnoverRate',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if (res.data.state != 0) {
          console.log(res.data.errorMessage);
          return;
        }
        page.setData({
          hotStockArray: res.data.data,
          hot_tab_selected: {
            increase: false,
            fall: false,
            turnOver: true
          }
        });
      }
    });
  },

  //跳转具体股票页面
  gotoStockDetail: function(event){
    var stockName = event.currentTarget.dataset.stockName;
    var stockCode, stockSinaCode;
    switch(stockName){
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
  getSinaCode: function(code){
    switch (code.charAt(0)){
      case "6":
      case "9":
        return "sh" + code;
      default:
      return "sz" + code;
    }
  },
  //自选中最新价排序
  sortByCurrentPrice: function(){
    var stocks = this.data.selectedStocks;

    //原来是降序，则改为升序
    if (this.data.select_sort.current_price_down){   
       stocks.sort(function (a, b) { return a.price > b.price ? 1 : -1});        
        this.setData({
          select_sort: {
            current_price_down: false,
            current_price_up: true            
          },
          selectedStocks: stocks
        });
    }//原来升序，改为降序 
    else if (this.data.select_sort.current_price_up){
      stocks.sort(function (a, b) { return a.price < b.price ? 1 : -1 });
      this.setData({
        select_sort: {
          current_price_down: true,
          current_price_up: false         
        },
        selectedStocks: stocks
      });
    }
    //其他变回来，改为降序
    else{
      stocks.sort(function (a, b) { return a.price < b.price ? 1 : -1 });
      this.setData({
        select_sort: {
          current_price_down: true,
          current_price_up: false,
          price_percent_down: false,
          price_percent_up: false,
          price_increase_down: false,
          price_increase_up: false,
        },
        selectedStocks: stocks
      });
    }
  },
  //自选中涨跌幅排序
  sortByPricePercent: function(){
    var stocks = this.data.selectedStocks;
    //原来是降序，则改为升序
    if (this.data.select_sort.price_percent_down) {
      stocks.sort(function (a, b) { return a.increasePercent > b.increasePercent ? 1 : -1 });   
      this.setData({
        select_sort: {
          price_percent_down: false,
          price_percent_up: true
        },
        selectedStocks: stocks
      });
    }//原来升序，改为降序 
    else if (this.data.select_sort.price_percent_up) {
      stocks.sort(function (a, b) { return a.increasePercent < b.increasePercent ? 1 : -1 });   
      this.setData({
        select_sort: {
          price_percent_down: true,
          price_percent_up: false
        },
        selectedStocks: stocks
      });
    }
    //其他变回来，改为降序
    else {
      stocks.sort(function (a, b) { return a.increasePercent < b.increasePercent ? 1 : -1 }); 
      this.setData({
        select_sort: {
          current_price_down: false,
          current_price_up: false,
          price_percent_down: true,
          price_percent_up: false,
          price_increase_down: false,
          price_increase_up: false,
        },
        selectedStocks: stocks
      });
    }
  },
  //自选中涨跌排序
  sortByPriceIncrease: function(){
    var stocks = this.data.selectedStocks;
    //原来是降序，则改为升序
    if (this.data.select_sort.price_increase_down) {
      stocks.sort(function (a, b) { return a.increase > b.increase ? 1 : -1 });   
      this.setData({
        select_sort: {
          price_increase_down: false,
          price_increase_up: true
        },
        selectedStocks: stocks
      });
    }//原来升序，改为降序 
    else if (this.data.select_sort.price_increase_up) {
      stocks.sort(function (a, b) { return a.increase < b.increase ? 1 : -1 });   
      this.setData({
        select_sort: {
          price_increase_down: true,
          price_increase_up: false
        },
        selectedStocks: stocks
      });
    }
    //其他变回来，改为降序
    else {
      stocks.sort(function (a, b) { return a.increase < b.increase ? 1 : -1 });   
      this.setData({
        select_sort: {
          current_price_down: false,
          current_price_up: false,
          price_percent_down: false,
          price_percent_up: false,
          price_increase_down: true,
          price_increase_up: false,
        },
        selectedStocks: stocks
      });
    }
  },
  //查看更多板块
  loadBlockMore: function(event){
      //获取板块类型
    var pageTitle = event.currentTarget.dataset.blockName;
    var sortType;
    for (var j = 0; j < this.data.blockClass.length; j++) {
      //成功返回，将errorMessage中放入code
      if (this.data.blockClass[j].name == pageTitle) {
        sortType = this.data.blockClass[j].code;
        break;
      }
    }
    wx.navigateTo({
      url: '../block_list/block_list?sortType=' + sortType + '&pageTitle=' + pageTitle,
    });    
  },
  //查看更多排行榜
  loadMoreHotTop: function(){
    var sortType ="increase";
    var pageTitle="涨幅排行榜";
    if (this.data.hot_tab_selected.increase){
      sortType = "increase"
      pageTitle = "涨幅排行榜";
    } else if (this.data.hot_tab_selected.fall){
      sortType = "fall";
      pageTitle = "跌幅排行榜";
    } else if (this.data.hot_tab_selected.turnOver) {
      sortType = "turnOver";
      pageTitle = "换手率排行榜";
    }
    wx.navigateTo({
      url: '../hot_list/hot_list?sortType=' + sortType + '&pageTitle=' + pageTitle,
    });    
  },
  //点击具体的板块
  clickSingleBlock: function(event){
    wx.navigateTo({
      url: '../block/block?blockName=' + event.currentTarget.dataset.blockName
        + '&blockCode=' + event.currentTarget.dataset.blockCode,
    });
  },
  //点击搜搜
  clickSearch: function(){
    wx.navigateTo({
      url: '../search/search',
    });
  }
});
