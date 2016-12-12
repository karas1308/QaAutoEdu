@Circle
Feature: TestCircle

  Scenario: 1 AreaCircle_r1
  Given I calculate area of circle
  When I check area of circle "3.14"

  Scenario: 1 AreaCircle_notNull
	Given I calculate area of circle1
	When I check area of circle1 null



#  //Проверяем вычисление плодащи круга, радиусом 1
#  @Test
#  public void testAreaCircle() {
#  double testarea = circle.area();
#  // System.out.println(testarea);
#  assertEquals("Площадь не равна " + testarea, testarea, 3.14, 0.01);
#  }
#
#  @Test
#  public void testAreaCircle_a0() {
#  String testarea1 = null;
#  testarea1 = String.valueOf(circle1.area());
#  //System.out.println(testarea1);
#  assertNotNull("Прилетело пустое значение ", testarea1);
#  }
#
#  @Test
#  public void testMoveCircle_10_10() {
#  circle.move(10, 10);
#  double[] move={circle.getX(),circle.getY()};
#  assertArrayEquals("Координаты после перемещения не совпали с ожиданием ", move, new double[]{20, 20},1);
#  }
#
#
#
#  //Проверим, что с увеличением стороны увеличится площадь
#  @Test
#  public void testResizeCircle_x2() {
#  circle.resize(2);
#  // System.out.println(circle.area());
#  assertFalse("Площадь не увеличилась и равна " + circle.area(), circle.area() < 12);
#
#  }
#
#  //Уменьшим сторону круга
#  @Test
#  public void testResizeCircle_x09() {
#  circle.resize(0.9);
#  // System.out.println(circle.a);
#  assertTrue("Сторона не уменьшилась  и равна " + circle.a, circle.a < 1);
#  }
#  }