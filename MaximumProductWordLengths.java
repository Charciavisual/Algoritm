public int maxProduct(String[] words) {
		int answer = 0;

		// 문자열을 구성하는 각 알파벳의 자리(a=0,,,z=25) 비트를 1로 표현한 int값을 생성한다.
		// ex) abef => 110011
		int[] bits = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			bits[i] = toBits(words[i]);
		}

		// 두 단어에서 공통으로 존재하는 알파벳이 없으면 bitA & bitB == 0 임을 이용
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if ((bits[i] & bits[j]) == 0) {
					answer = Math.max(answer, words[i].length() * words[j].length());
				}
			}
		}
		return answer;
	}

	private int toBits(String s) {
		int bit = 0;
		for (char ch : s.toCharArray()){
			bit |= (1 << (ch - 'a'));
    }
		return bit;
	}
