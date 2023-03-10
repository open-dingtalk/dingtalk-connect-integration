# 关于集成连接平台
连接平台是帮助企业、三方、官方应用之间实现快速集成的平台。它是通过将各种系统包装为形式各样的连接器，通过建立连接流的形式，将不同系统的数据、业务流程进行流连接传递，从而实现集成的。
另一方面，通过集成钉钉连接平台，可以将连接平台现有连接器、连接流集成到其它的系统、产品中，事件、接口、各种编排好现成的集成流都可以被使用。集成连接平台的好处有：
1. 在集成成本上：
   1. 自有系统、本地化部署的产品等，只要有连接器就可使用，无须二次对接。
   2. 尽量避免对接已有接口时二次开发的情况，连接平台可以实现接口鉴权、字段调整、甚至重新组合逻辑。
2. 对于功能性上：
   1. 更多的事件类型可以接入，涵盖SaaS、官方、企业自有系统，数据的获取更广泛。
   2. 提供专有的执行记录和运行告警，更加稳定可靠。

# 常见基本名词
连接器
> 连接器是触发事件和执行动作的集合，是提供一套系统输入和输出接入的封装  

执行动作
> 连接平台调用外部系统API来实现某个操作。

触发事件/触发器
> 外部系统以某种方式告诉连接平台发生了某个事件。

连接流
> 连接流通过低代码方式编排、组合触发事件和执行动作，来连接多个应用系统，实现业务流程自动化。

集成流
> 连接流的一种，可以像API一样被调用、或者被集成到其它系统中，像执行动作一样有输入有返回

连接资产
> 以上概念都属于企业下的连接资产，泛指通过连接平台接入的属于企业的功能权益。  

# 快速上手
这里提供一个快速上手接入的例子，按照流程可以体验连接器的快速接入使用。

## 使用范围与授权
### 企业自建应用接入
> 如何开发企业自建应用 [点击进入](https://open.dingtalk.com/document/org/application-types)

对于企业本身，可使用的连接器都属于企业下的权益资产，对于自建应用自然开放。

### 三方SaaS应用接入
> 如何开发三方应用 [点击进入](https://open.dingtalk.com/document/isvapp-server/call-server-apis-3)

与自建应用不同，第三方SaaS接入使用时，对与非使用组织下的连接器，使用之前需要先通过平台审核。审核通过后，在调用连接器前，需要用户授权同意，否则将会返回未授权的错误信息。因此使用前需要以下步骤：

1. 接入连接平台  

![1.1 进入场域管理菜单](https://img.alicdn.com/imgextra/i2/O1CN01RpLeNN1zTWOFPwhJe_!!6000000006715-2-tps-1910-745.png)
<!--suppress XmlDeprecatedElement -->
<center>1.1 进入场域管理菜单</center>

![1.2 选择新增](https://img.alicdn.com/imgextra/i3/O1CN018e2Prx1KZ6ycFy2f8_!!6000000001177-2-tps-494-445.png)
<!--suppress XmlDeprecatedElement -->
<center>1.2 选择新增</center>

![1.3 配置应用作为连接器接入场域](https://img.alicdn.com/imgextra/i2/O1CN01f5NXFK1PsPkdpATSu_!!6000000001896-2-tps-527-371.png)
<!--suppress XmlDeprecatedElement -->
<center>1.3 配置应用作为连接器接入场域</center>

2. 配置连接器的使用范围并提交审核

![2.1 申请连接器的使用范围](https://img.alicdn.com/imgextra/i2/O1CN0192IuoX2A0Ami1lizi_!!6000000008140-2-tps-1345-902.png)
> 注意：企业自建连接器不需要申请使用范围
<!--suppress XmlDeprecatedElement -->
<center>2.1 申请连接器的使用范围</center>

3. 调用前需要、经过企业用户授权同意

![3.1 使用授权](https://img.alicdn.com/imgextra/i3/O1CN01XrC3wJ1bwKWaBYUiK_!!6000000003529-2-tps-319-487.png)
<!--suppress XmlDeprecatedElement -->
<center>3.1 使用授权</center>

### 配置、启动样例应用
> 自建应用配置项  
> dingtalk.appKey 		自建应用appKey  
> dingtalk.appSecret 	自建应用appSecret  
> 三方应用配置项  
> dingtalk.suiteKey	三方应用suiteKey  
> dingtalk.suiteSecret	三方应用suiteSecret  
> 备注说明：  
> 两套参数成对出现，如果配置了4个参数，优先生效为自建应用模式  

```shell
# 以企业自建应用启动
java -jar dingtalk-connect-integration.jar --dingtalk.appKey=xxx --dingtalk.appSecret=xxx
```

```shell
# 以三方应用启动
java -jar dingtalk-connect-integration.jar --dingtalk.suiteKey=xxx --dingtalk.suiteSecret=xxx
```

### 操作使用连接器
1. 选择需要使用的连接器
> ![](https://img.alicdn.com/imgextra/i1/O1CN01MXUbCi1wwQXZlOUD6_!!6000000006372-2-tps-626-336.png)
2. 选择需要使用的执行动作
> ![](https://img.alicdn.com/imgextra/i3/O1CN01yRcunw27ZwsDX6xWf_!!6000000007812-2-tps-1055-271.png)
3. 查看执行动作详情
> 选择执行动作后样本工程会返回
> * 基本信息
> * 当前的执行动作是否有授权过
> * 出入参的数据结构
> 
> ![](https://img.alicdn.com/imgextra/i1/O1CN01NneU7x1GDo5kAWotT_!!6000000000589-2-tps-853-835.png)
4. 申请并授权（三方SaaS情况）
> 针对开发者：  
> 特殊的，由于接入的应用不是当前组织的情况，会要求当前组织给予授权，否则后续的执行操作会失败。  
> 请求接口后，如果当前的连接资产（执行动作/触发事件/集成流）未有授权，会返回授权状态为未授权。  
> 针对使用者：  
> 授权后接入方才可使用对应的连接资产（执行动作/触发事件/集成流），授权可以在当前组织开发者后台连接平台板块进行管理，可随时取消授权。

![](https://img.alicdn.com/imgextra/i2/O1CN01f76Mzv1UAVrb43ZnB_!!6000000002477-2-tps-865-406.png)
5. 创建连接实例
6. 发起调用

## 开发集成说明
> 这里使用样例应用的代码进行说明：  
> 样例后端程序使用 spring-boot 系列框架开发    
> 前端使用 react + antd开发  
> 下面按照各操作用到的接口进行说明：

#### 查询可用的连接器
连接平台提供了开放接口，供集成方查询当前集成方在组织下可以使用的连接器信息列表  

> 接口:	/v1.0/connector/assets/connectors
> 方法:	GET	 
> 格式:	json   
> 入参(query): 
> ```typescript
> {
>    type : string;
>    nextToken?: string;
>    maxResults?: number;
> }
> ```
> 出参(body): 
> ```typescript
> {
>    hasMore?: boolean;
>    nextToken?: string;
>    list: {
>       id: string; // 连接器的ID
>       name: string; // 连接器的名称
>       description?: string; // 相关描述
>       providerCorpId: string; // 连接器提供组织
>       icon?: string; // 图标
>    }[];
> }
> ```

```java
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryConnectorsRequest extends DingRequest<QueryConnectorsResponse> {
   private final String type;
   private final long nextToken;
   private final long size;

   public QueryConnectorsRequest(String type, long nextToken, long size) {
      this.type = type;
      this.nextToken = nextToken;
      this.size = size;
   }

   public QueryConnectorsRequest(String type, long nextToken) {
      this(type, nextToken, 50);
   }

   public QueryConnectorsRequest(String type) {
      this(type, 0L);
   }

   @Override
   protected RequestEntity<?> toEntity() {
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/connectors")
              .queryParamIfPresent("type", Optional.ofNullable(type))
              .queryParamIfPresent("nextToken", Optional.of(nextToken))
              .queryParamIfPresent("maxResults", Optional.of(size));
      return RequestEntity.get(uriBuilder.build().toUriString()).build();
   }
}
```

连接器类型包含三种对应的type枚举：
* 企业自建 - self_built: 企业下自行开发创建的连接器。
* 三方已上架连接器 - third_party：由三方提供并上架到连接器市场的应用，需要安装对应的应用并订阅连接器才可使用。
* 官方提供 - official：由官方提供，企业可直接使用的连接器。

对于同一个组织，接入连接平台的应用是企业自建应用还是三方应用，其查询该组织下不同类型的连接器结果是存在差异的：
* 对于自建应用：可以直接查询到该组织可以使用的自建、三方、官方连接器。
* 对于三方应用：可以直接查询到该组织可以使用的自建应用，可以查询到在已申请通过范围内的三方、官方连接器。

#### 获取连接器下支持的执行动作
执行动作的查询和使用与连接器的范围限制规则基本相同  
> 接口:	/v1.0/connector/assets/actions/search
> 说明:	查询连接器列表  
> 方法:	GET  
> 格式:	json  
> 入参(query):   
> ```typescript
> {
>    connectorId : string; // 连接器ID
>    connectorProviderCorpId: string; // 连接器提供组织的ID
>    integrationTypes: array<string>; // 集成类型，默认只有basic，高阶集成类型integration_endpoint 暂不开放
>    nextToken?: string;
>    maxResults?: number;
> }
> ```
> 出参(body): 
> ```typescript
> {
>    hasMore?: boolean;
>    nextToken?: string;
>    list: {
>        id: string; //执行动作的的ID
>        connectorId: string; // 连接器的ID
>        name: string;
>        providerCorpId: string;
>        integrationType: string; // 集成类型
>        connectAssetUri: string; // 连接器资产唯一标识
>        authorized: boolean; // 是否已授权
>        authorityUrl: string; // 授权页地址
>    }[];
> }
> ```

```java
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QueryActionsRequest extends DingRequest<QueryActionResponse> {
   private String nextToken;
   private long maxResults;
   private final String connectorId;
   private final String connectorProviderCorpId;
   private List<String> integrationTypes;

   public QueryActionsRequest(Long nextToken, long maxResults, String connectorId, String connectorProviderCorpId, List<String> integrationTypes) {
      this.nextToken = String.valueOf(nextToken);
      this.maxResults = maxResults;
      this.connectorId = connectorId;
      this.connectorProviderCorpId = connectorProviderCorpId;
      this.integrationTypes = integrationTypes;
   }

   public QueryActionsRequest(String connectorId, String connectorProviderCorpId) {
      this(0L,50L, connectorId, connectorProviderCorpId, Collections.singletonList("basic"));
   }

   @Override
   protected RequestEntity<?> toEntity() {
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/actions/search");
      return RequestEntity.post(uriBuilder.build().toUriString())
              .contentType(MediaType.APPLICATION_JSON)
              .body(this);
   }
}
```

#### 获取执行动作的详情
> 接口:	/v1.0/connector/assets/actions/details/query
> 说明:	获取详情  
> 方法:	GET  
> 格式:	json  
> 入参(query):   
> ```typescript
> {
>    connectAssetUri : string; // 执行动作资产标识
> }
> ```
> 出参(body): 
> ```typescript
> {
>    inputSchema: string; // 入参格式， json-schema文本
>    outputSchema: string; // 出参格式,  json-schema文本
>    refId: string; // 执行动作的ID
>    refType: string; // 资产类型，执行动作为action
>    refProviderCorpId: string; // 执行动作的提供组织ID
> }
> ```

```java
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetActionDetailRequest extends DingRequest<GetActionDetailResponse> {
    private final String connectAssetUri;
    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/actions/details/query");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
```
#### 建立连接实例
> 连接实例是集成方调用执行动作/集成流等连接资产时需要创建的实例
> 实例中包含了，调用资产的配置（如账号鉴权、环境变量等，目前配置未对外开放）
> 通过调用实例，调用连接资产

> 接口:	/v1.0/connector/asset/action/invoker  
> 说明:	建立连接实例  
> 方法:	POST  
> 格式:	json  
> 入参(query):  
> ```typescript
> {
>    connectAssetUri : string; // 执行动作资产标识
>    instanceKey: string: // 连接实例唯一标识
> }
> ```
> 出参(body): 
> ```typescript
> {
> }
> ```
```java
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CreateInvokerRequest extends DingRequest<UpdateInvokerResponse> {
    private final String connectAssetUri;

    private final String instanceKey;
    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/instances");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
```
#### 发起调用
> 接口:	/v1.0/connector/instances/invoke   
> 说明:	调用连接实例  
> 方法:	POST  
> 格式:	json  
> 入参query:  
> ```typescript
> {
>    connectAssetUri : string; // 执行动作资产标识
>    instanceKey: string: // 连接实例唯一标识
>    inputJsonString: string; // 入参，JSON格式传入
> }
> ```
> 出参body: 
> ```typescript
> {
>       outputJson: string; // 出参 ,JSON格式
>       instanceId: string; // 本次调用记录的ID
>       cost: number; // 整个调用耗时
> }
> ```

```java
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvokeInstanceRequest extends DingRequest<InvokeInstanceResponse> {
    private final String connectAssetUri;

    private final String instanceKey;

    private final String inputJsonString;

    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/instances/invoke");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
```

## 常见集成错误
4001 - 参数错误  
4003 - 调用发起未授权  
4004 - 连接资产不存在/调用的实例不存在  