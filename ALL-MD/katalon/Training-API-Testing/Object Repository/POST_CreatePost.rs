<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Bikin Data https://jsonplaceholder.typicode.com/posts</description>
   <name>POST_CreatePost</name>
   <tag></tag>
   <elementGuidId>4952c23b-8e29-42f3-aa20-e1ce6be7d231</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;userId\&quot;: 1,\n  \&quot;id\&quot;: 101,\n  \&quot;title\&quot;: \&quot;coba jsonplace holder\&quot;,\n  \&quot;body\&quot;: \&quot;body language\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>1561a5ac-7df5-4822-a8aa-146851ddc24f</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>11.2.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://jsonplaceholder.typicode.com/posts</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()
ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()


WS.verifyElementPropertyValue(response, 'userId', 1L)
WS.verifyElementPropertyValue(response, 'id', 101)
WS.verifyElementPropertyValue(response, 'title', 'coba jsonplace holder')
WS.verifyElementPropertyValue(response, 'body', 'body language')

WS.verifyResponseStatusCode(response, 200)

// Ambil nilai &quot;id&quot; dari jawaban server, lalu pastikan nilainya = 1
def idDariServer = WS.getElementPropertyValue(response, 'id')
assert idDariServer == '1'</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
