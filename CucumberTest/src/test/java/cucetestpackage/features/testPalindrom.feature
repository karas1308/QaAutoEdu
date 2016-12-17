@Palindrom
  Feature: Palindrom
 #  Проверим, что слово - палиндром
	Scenario: 1 testСheckWordTrue
	  Given Введем текст "q1qq1q"
	  When Узнаем палиндром ли это
	  Then Что-то напишем, если вдруг ошибка
#Проверим, что слово не палиндром
	Scenario: 2 testСheckWordFalse
	  Given Введем текст "Q1qqQ"
	  When Узнаем палиндром ли это
	  Then Сообщим об ошибке
#	  Проверим, что фраза палиндром
	Scenario: 3 testcheckPhraseTrue
	  Given Введем фразу-палиндром "Qwert asd d sa trew q"
	  When Узнаем палиндром ли эта фраза
	  Then Что-то напишем, если вдруг ошибка
  #	  Проверим, что фраза палиндром
	Scenario: 4 testcheckPhraseFalse
	  Given Введем фразу-палиндром "Qwert asd d sa trew q1"
	  When Узнаем палиндром ли эта фраза
	  Then Сообщим об ошибке

