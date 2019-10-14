package posmachine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator calc = new PriceCalculator();
        PosMachine myPosMachine = new PosMachine(calc);

        //then
        Assertions.assertThrows(UnsupportedOperationException.class,() ->{
            myPosMachine.getReceipt("Test");
        });
    }

    @Test
    public void should_get_receipt_using_stub_price_calculator() {
        //given
        PriceCalculator calc = new StubPriceCalculator();
        PosMachine myPosMachine = new PosMachine(calc);

        //when
        String receipt = myPosMachine.getReceipt("Peanuts");

        //then
        Assertions.assertEquals("Name: Peanuts, Price: 45.0", receipt);
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator mockedCalculator = mock(PriceCalculator.class);
        PosMachine myPosMachine = new PosMachine(mockedCalculator);
        when(mockedCalculator.calculate(eq("Peanuts"))).thenReturn(14.0);

        //when
        String receipt = myPosMachine.getReceipt("Peanuts");

        //then
        Assertions.assertEquals("Name: Peanuts, Price: 14.0", receipt);
    }


    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 45.0;
        }
    }
}
