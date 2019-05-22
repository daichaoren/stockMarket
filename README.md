# stockMarket
![java 1.8+](https://img.shields.io/badge/JAVA-1.8+-green.svg)
![build passing](https://img.shields.io/appveyor/ci/gruntjs/grunt.svg)
![build passing](https://img.shields.io/apm/l/vim-mode.svg)	

A股行情展示小程序，包含小程序前台、后台，支持个股、板块、排行以及自选股功能

## 前言

#### market文件夹为前端小程序原生开发，可以直接用微信开发工具打开
#### 统一访问的ip和端口在app.js的Globledata中配置
#### stock文件夹为后端，应用spring boot 和spring data jpa 开发
#### 数据库用的mysql，如果想用其他数据库，在stock/src/main/resources/application.yml做相应的配置即可


## 截图

![](https://github.com/daichaoren/stockMarket/raw/master/img/firstPage.png)  

![](https://github.com/daichaoren/stockMarket/raw/master/img/hotTop.png) 

![](https://github.com/daichaoren/stockMarket/raw/master/img/stock.png) 

![](https://github.com/daichaoren/stockMarket/raw/master/img/selected.png) 

![](https://github.com/daichaoren/stockMarket/raw/master/img/blockList.png) 

![](https://github.com/daichaoren/stockMarket/raw/master/img/blockType.png) 

## 结语
 前端开发工具就是用的微信开发工具
 
 后端用的是idea
 
 个股行情数据用的是新浪财经，板块和排行榜用的是东方财富，分时和K线图只画了个示例，具体数据准备今后应用腾讯股票数据
 
 如果有什么问题请给我提issue
 
 #### 赞助
 
 ![](https://github.com/daichaoren/stockMarket/raw/master/img/wx.png) 
 ![](https://github.com/daichaoren/stockMarket/raw/master/img/ali.png)
