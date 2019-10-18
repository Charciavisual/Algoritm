package template;

public class BitMask {
    public void bitMask() {
        int a = 5, b = 7;
        //비트 연산
        //합집합 0101 | 0111 = 0111
        System.out.println(a | b);
        //교집합 0101 & 0111 = 0101
        System.out.println(a & b);
        // 차집합 0101 & 1000 = 0000
        System.out.println(a & (~b));
        // 토글 0101 ^ 0001 = 0100
        System.out.println(a ^ 1); //a의 2^0 위치의 비트를 토글

        //자바 메소드
        //비트 개수
        Integer.bitCount(a);
        //최하위 비트 위치, 2^k 형태로 계산되며 k가 리턴됨, 2^0번째 비트가 1이면 리턴값 0
        Integer.numberOfTrailingZeros(a);

        System.out.println(Integer.bitCount(a));
        System.out.println(Integer.numberOfTrailingZeros(2));

        //마지막 원소 지우기
        System.out.println(a &= (a - 1));

        //마지막 비트 추출
        // 0101 & 1011 = 0001
        //numberOfTrailingZeros 랑 다른점 : 위치를 리턴하는지, 값이 리턴되는지의 차이
        // numberOfTrailingZeros => 0, a & (-a) => 1
        System.out.println(a & (-a));

        //모든 부분집합 만들기
        int set = 15; // 1111
        for (int subset = set; subset > 0; subset = ((subset - 1) & set)) {
            //do anything
        }
    }
}
