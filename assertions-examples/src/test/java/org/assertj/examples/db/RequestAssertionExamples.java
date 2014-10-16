package org.assertj.examples.db;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.api.ValueType;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Request;
import org.assertj.db.type.Source;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

public class RequestAssertionExamples extends AbstractAssertionsExamples {

  /**
   * This example shows a simple case of test.
   */
  @Test
  public void basic_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from albums");

    // On the values of a column by using the name of the column
    assertThat(request).column("title")
        .value().isEqualTo("Boy")
        .value().isEqualTo("October")
        .value().isEqualTo("War")
        .value().isEqualTo("Under a Blood Red Sky")
        .value().isEqualTo("The Unforgettable Fire")
        .value().isEqualTo("Wide Awake in America")
        .value().isEqualTo("The Joshua Tree")
        .value().isEqualTo("Rattle and Hum")
        .value().isEqualTo("Achtung Baby")
        .value().isEqualTo("Zooropa")
        .value().isEqualTo("Pop")
        .value().isEqualTo("All That You Can't Leave Behind")
        .value().isEqualTo("How to Dismantle an Atomic Bomb")
        .value().isEqualTo("No Line on the Horizon")
        .value().isEqualTo("Songs of Innocence");

    // On the values of a row by using the index of the row
    assertThat(request).row(1)
        .value().isEqualTo(2)
        .value().isEqualTo(DateValue.of(1981, 10, 12))
        .value().isEqualTo("October")
        .value().isEqualTo(11)
        .value().isEqualTo(TimeValue.of(0, 41, 8))
        .value().isNull();
  }

  /**
   * This example shows a simple case of test on column.
   */
  @Test
  public void basic_column_request_assertion_examples() {
    Source source = new Source("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    Request request = new Request(source, "select * from albums");

    assertThat(request).column("title")
        .haveValuesEqualTo("Boy", "October", "War", "Under a Blood Red Sky", 
            "The Unforgettable Fire", "Wide Awake in America", "The Joshua Tree", 
            "Rattle and Hum", "Achtung Baby", "Zooropa", "Pop", "All That You Can't Leave Behind",
            "How to Dismantle an Atomic Bomb", "No Line on the Horizon", "Songs of Innocence");
  }

  /**
   * This example shows a simple case of test on row.
   */
  @Test
  public void basic_row_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from albums");

    assertThat(request).row(1)
        .haveValuesEqualTo(2, DateValue.of(1981, 10, 12), "October", 11, TimeValue.of(0, 41, 8), null)
        .haveValuesEqualTo("2", "1981-10-12", "October", "11", "00:41:08", null);
  }

  /**
   * This example shows how test the size.
   */
  @Test
  public void size_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from albums");

    // There is assertion to test the column and row size.
    assertThat(request).hasColumnsSize(6);
    assertThat(request).hasRowsSize(15);

    // There are equivalences of these size assertions on the column and on the row.
    assertThat(request).column().hasSize(15);
    assertThat(request).row().hasSize(6);
  }

  /**
   * This example shows the inclusion of columns in table.
   */
  @Test
  public void request_parameters_examples() {
    Request request = new Request(dataSource, "select release, title from albums where title like ?", "A%");

    assertThat(request).hasColumnsSize(2).hasRowsSize(2);
    assertThat(request)
        .row().hasSize(2).haveValuesEqualTo("1991-11-18", "Achtung Baby")
        .row().haveValuesEqualTo("2000-10-30", "All That You Can't Leave Behind");
  }

  /**
   * This example shows that the numeric value can be test with text.
   */
  @Test
  public void text_for_numeric_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from members");

    assertThat(request).row(1)
        .value("size").isEqualTo("1.77").isNotEqualTo("1.78");

    Request request1 = new Request(dataSource, "select * from albums");

    assertThat(request1).row(14)
        .value("numberofsongs").isEqualTo("11").isNotEqualTo("12");
  }

  /**
   * This example shows tests on numeric values.
   */
  @Test
  public void numeric_request_assertion_examples() {
    Source source = new Source("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    Request request = new Request(source, "select * from members");

    assertThat(request).row(1)
        .value("size").isNotZero()
            .isGreaterThan(1.5).isGreaterThanOrEqualTo(1.77)
            .isLessThan(2).isLessThanOrEqualTo(1.77);

    Request request1 = new Request(dataSource, "select * from albums");

    assertThat(request1).row(14)
        .value("numberofsongs").isNotZero()
            .isGreaterThan(10).isGreaterThanOrEqualTo(11)
            .isLessThan(11.5).isLessThanOrEqualTo(11);
  }

  /**
   * This example shows boolean assertions.
   */
  @Test
  public void boolean_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from albums");

    assertThat(request).column("live")
        .value(3).isTrue()
        .value().isNull()
        .value().isEqualTo(true).isNotEqualTo(false);
  }

  /**
   * This example shows type assertions.
   */
  @Test
  public void type_request_assertion_examples() {
    Source source = new Source("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    Request request = new Request(source, "select * from albums");

    assertThat(request).row(3)
        .value().isNumber()
        .value().isDate().isOfAnyOfTypes(ValueType.DATE, ValueType.NUMBER)
        .value().isText()
        .value().isNumber().isOfType(ValueType.NUMBER)
        .value().isTime()
        .value().isBoolean();
  }

  /**
   * This example shows type assertions on column.
   */
  @Test
  public void colum_type_request_assertion_examples() {
    Request request = new Request(dataSource, "select * from albums");

    assertThat(request)
        .column().isNumber(false)
        .column().isDate(false).isOfAnyOfTypes(ValueType.DATE, ValueType.NUMBER)
        .column().isText(false)
        .column().isNumber(false).isOfType(ValueType.NUMBER, false)
        .column().isTime(false)
        .column().isBoolean(true);
  }

  /**
   * This example shows possible assertions on the date.
   * In this example, we can see that the assertions are 
   */
  @Test
  public void date_request_assertion_examples() {
    Source source = new Source("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    Request request = new Request(source, "select * from members");

    // Compare date to date or date in string format
    assertThat(request).row(1)
        .value("birthdate")
            .isEqualTo(DateValue.of(1961, 8, 8))
            .isEqualTo("1961-08-08")
            .isNotEqualTo("1968-08-09")
            .isNotEqualTo(DateValue.of(1961, 8, 9))
            .isAfter("1961-08-07")
            .isAfter(DateValue.of(1961, 8, 7))
            .isBefore("1961-08-09")
            .isBefore(DateValue.of(1961, 8, 9))
            .isAfterOrEqualTo("1961-08-08")
            .isAfterOrEqualTo(DateValue.of(1961, 8, 7))
            .isBeforeOrEqualTo("1961-08-08")
            .isBeforeOrEqualTo(DateValue.of(1961, 8, 9));

    // Compare date to date/time or date/time in string format
    assertThat(request).row(1)
        .value("birthdate")
            .isEqualTo(DateTimeValue.of(DateValue.of(1961, 8, 8), TimeValue.of(0, 0, 0)))
            .isEqualTo("1961-08-08T00:00")
            .isNotEqualTo("1961-08-08T00:00:01")
            .isNotEqualTo(DateTimeValue.of(DateValue.of(1961, 8, 8), TimeValue.of(0, 0, 1)))
            .isAfter("1961-08-07T23:59")
            .isAfter(DateTimeValue.of(DateValue.of(1961, 8, 7)))
            .isBefore("1961-08-08T00:00:01")
            .isBefore(DateTimeValue.of(DateValue.of(1961, 8, 8), TimeValue.of(0, 0, 1)))
            .isAfterOrEqualTo("1961-08-08T00:00")
            .isAfterOrEqualTo(DateValue.of(1961, 8, 7))
            .isBeforeOrEqualTo("1961-08-08T00:00:00.000000000")
            .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(1961, 8, 9), TimeValue.of(0, 0, 1, 3)));
  }
}
