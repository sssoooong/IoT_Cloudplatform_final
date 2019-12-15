# IoT_Cloudplatform_final

아두이노 센서를 통해 일산화탄소의 값을 받고 일산화탄소 농도에 따라 LED 가 ON/OFF 되며 안드로이드를 통해 모터를 ON/OFF 할 수 있는 코드이다.

(흡연실에서 일산화 농도를 체크하고 농도가 높으면 LED 를 통해 알려준다. 사용자는 안드로이드를 통해 모터를 ON/OFF 한다.)

아두이노 센서값(일산화탄소 농도, LED ON/OFF, MOTOR ON/OFF)을 AWS DB에 쌓고 이 DB의 table 값을 바탕으로 

안드로이드에서 사물목록 조회를 할 수 있고 사물(일산화탄소, LED, MOTOR)의 상태 조회할 수 있으며 MOTOR의 ON/OFF 를
변경할 수 있도록 하였다. 또한, 사물로그 조회도 가능하다.

