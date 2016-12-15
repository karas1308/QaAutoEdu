@Palindrom


  Feature: Palindrom
 #  Проверим, что слово - палиндром
	Scenario: 1 testСheckWordTrue
	  Given Введем текст "q1qq1q"
	  When Узнаем палиндром ли это
	  Then Что-то напишем, если вдруг ошибка

#Проверим, что слово не палиндром
	Scenario: 2 testСheckWordFalse
	  Given Введем текст не палиндром "Q1qqQ"
	  When Проверим, что это не палиндром
	  Then Сообщим об ошибке

#	  Проверим, что фраза палиндром
	Scenario: 3 testcheckPhraseTrue
	  Given Введем фразу-палиндром "Qwert asd d sa trew q"
	  When Узнаем палиндром ли эта фраза
	  Then Сообщим если не тру

  #	  Проверим, что фраза палиндром
	Scenario: 4 testcheckPhraseFalse
  Given Введем фразу-не палиндром "Qwert asd d sa trew q1"
  When Проверим, что фраза не палиндром
  Then Сообщим, если она палиндром

