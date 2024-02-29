# flink_realtime_eb
flink实时电商数仓处理

gmall-mock 模拟数据生成父工程
--gmall-common    模拟数据生成公用模块
--gmall-mock-db   业务数据模拟生成服务
--gmall-mock-log  行为日志数据模拟生成服务

gmall-parent  父工程
--gmall-logger     日志采集服务
--gmall-publisher  最终数据查询服务
--gmall-realtime   flink实时处理程序


