package Model;

import java.io.Serializable;
import java.util.Comparator;

public class PriceComparator implements Comparator<LargeParcel> , Serializable{

	@Override
	public int compare(LargeParcel o1, LargeParcel o2) {
		double totalPrice1=0,totalPrice2=0;
		double totalWeight1=0,totalWeight2=0;
		for(Item it : o1.listOfItem){
			totalPrice1+=it.getPrice();
			totalWeight1+=it.getItemWeight();
		}
		for(Item it : o2.listOfItem){
			totalPrice2+=it.getPrice();
			totalWeight2+=it.getItemWeight();
		}
		if (totalPrice1/totalWeight1>totalPrice2/totalWeight2)
			return 1;
		if (totalPrice1/totalWeight1<totalPrice2/totalWeight2)
			return -1;
		return 0;
	}

}
