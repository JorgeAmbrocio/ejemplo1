package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

	String aux = "";
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int ESTADO_CADENA = 2;
	private final int YYINITIAL = 0;
	private final int COMENTARIO_MULTI = 1;
	private final int yy_state_dtrans[] = {
		0,
		106,
		108
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NO_ANCHOR,
		/* 232 */ YY_NO_ANCHOR,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NO_ANCHOR,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NO_ANCHOR,
		/* 247 */ YY_NO_ANCHOR,
		/* 248 */ YY_NO_ANCHOR,
		/* 249 */ YY_NO_ANCHOR,
		/* 250 */ YY_NO_ANCHOR,
		/* 251 */ YY_NO_ANCHOR,
		/* 252 */ YY_NO_ANCHOR,
		/* 253 */ YY_NO_ANCHOR,
		/* 254 */ YY_NO_ANCHOR,
		/* 255 */ YY_NO_ANCHOR,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NO_ANCHOR,
		/* 260 */ YY_NO_ANCHOR,
		/* 261 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"4:9,5,1,4:2,2,4:18,5,16,7,4:2,10,18,57,11,12,6,8,20,9,28,3,56:10,24,21,14,1" +
"3,15,25,4,58:2,53,51,58:4,52,58:9,42,58:7,26,4,27,19,55,4,40,35,38,32,37,48" +
",43,39,29,58,50,36,54,30,33,47,58,41,44,31,34,46,49,45,58:2,22,17,23,4:51,5" +
"8,4:17,58,4:8020,58,4:57319,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,262,
"0,1:2,2,3,1:3,4,5,1:3,6,7,8,9,1:10,10,11,1:9,12,13,1,12:5,14,1,12:6,15,12:6" +
",16,12:24,1,17,1:4,18,19,20,21,22,23,24,25,14,23,26,27,28,29,30,31,32,33,34" +
",35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59" +
",60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84" +
",85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,1" +
"07,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125," +
"126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144" +
",145,146,147,148,149,12,150,151,152,153,154,155,156,157,158,159,160,161,162" +
",163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,18" +
"1,182,183,184")[0];

	private int yy_nxt[][] = unpackFromString(185,59,
"1,2,3,4,5,3,6,7,8,9,10,11,12,13,14,15,16,93,98,17,18,19,20,21,22,23,24,25,2" +
"6,27,94,200,99,226:2,240,226,245,248,226,250,251,252,253,254,226,255,256,25" +
"7,258,226:5,101,28,104,226,-1:61,3,-1:2,3,-1:56,92,-1:2,29,-1:60,30,-1:59,3" +
"1,-1:62,32,-1:58,33,-1:58,34,-1:58,35,-1:74,226,102,226:17,38,226:5,259,226" +
":2,-1,226,-1:28,100,-1:27,28,-1:31,226:28,-1,226,-1:29,226:5,208,226:22,-1," +
"226,-1:56,46,-1:31,226:26,168,226,-1,226,-1:29,226:2,181,226:4,182,226:20,-" +
"1,226,-1:2,87,-1:2,87,-1:54,40,95,92:56,-1:17,36,-1:70,226:5,260,226:2,105," +
"226:19,-1,226,-1,40,-1:60,89,-1:84,127:26,97,127,-1,127,-1:18,37,-1:69,226:" +
"4,39,226:3,111,226:19,-1,226,-1:29,226:2,41,226:12,204,226:12,-1,226,-1:57," +
"47,-1:4,103:56,-1:29,226:20,42,226:7,-1,226,1,86,87,88:2,87,96,88:52,-1:29," +
"226:22,228,230,129,226:3,-1,226,1,-1:2,90:4,91,90:51,-1:29,130,226:27,-1,22" +
"6,-1:29,226:5,131,226:22,-1,226,-1:29,226:19,242,226:8,-1,226,-1:29,226:4,2" +
"09,226:23,-1,226,-1:29,226:8,246,226:19,-1,226,-1:29,226:15,132,226:12,-1,2" +
"26,-1:29,226:2,133,226:25,-1,226,-1:29,226,134,226:26,-1,226,-1:29,226:11,1" +
"35,226:16,-1,226,-1:29,226:2,138,226:8,139,226:16,-1,226,-1:29,226:12,140,2" +
"26:15,-1,226,-1:29,226:11,234,43,226:15,-1,226,-1:29,226:18,210,226:9,-1,22" +
"6,-1:29,226:20,44,226:7,-1,226,-1:29,226:6,232,226:21,-1,226,-1:29,142,226:" +
"3,247,226:23,-1,226,-1:29,226:12,45,226:15,-1,226,-1:29,226:7,212,226:20,-1" +
",226,-1:29,127:28,-1,127,-1:29,226:7,48,226:20,-1,226,-1:29,226:10,236,226:" +
"17,-1,226,-1:29,226:15,49,226:12,-1,226,-1:29,226:8,50,226:19,-1,226,-1:29," +
"226:8,51,226:19,-1,226,-1:29,226:8,148,226:19,-1,226,-1:29,226:2,214,226:25" +
",-1,226,-1:29,226:15,149,226:12,-1,226,-1:29,226:12,52,226:15,-1,226,-1:29," +
"226:8,53,226:19,-1,226,-1:29,226:5,237,226:22,-1,226,-1:29,226:3,54,226:24," +
"-1,226,-1:29,218,226:27,-1,226,-1:29,226:3,55,226:24,-1,226,-1:29,226,154,2" +
"26:15,244,226:10,-1,226,-1:29,226:11,156,226:16,-1,226,-1:29,226:7,158,226:" +
"20,-1,226,-1:29,226:12,159,226:15,-1,226,-1:29,226:2,56,226:25,-1,226,-1:29" +
",226:21,57,226:6,-1,226,-1:29,226,165,226:26,-1,226,-1:29,226:15,58,226:12," +
"-1,226,-1:29,226:10,59,226:17,-1,226,-1:29,170,226:27,-1,226,-1:29,226:12,6" +
"0,226:15,-1,226,-1:29,226:9,171,226:18,-1,226,-1:29,226:2,61,226:25,-1,226," +
"-1:29,226:8,174,226:19,-1,226,-1:29,226:7,62,226:20,-1,226,-1:29,226:8,63,2" +
"26:19,-1,226,-1:29,226:8,64,226:19,-1,226,-1:29,226:2,65,226:25,-1,226,-1:2" +
"9,226:6,221,226:21,-1,226,-1:29,226:12,66,226:15,-1,226,-1:29,226:8,67,226:" +
"19,-1,226,-1:29,226:7,176,226:20,-1,226,-1:29,226:11,177,226:16,-1,226,-1:2" +
"9,226:3,178,226:24,-1,226,-1:29,226,179,226:26,-1,226,-1:29,226,68,226:26,-" +
"1,226,-1:29,226:19,180,226:8,-1,226,-1:29,226:14,69,226:13,-1,226,-1:29,226" +
":9,70,226:18,-1,226,-1:29,226:10,71,226:17,-1,226,-1:29,226:9,72,226:18,-1," +
"226,-1:29,226:2,183,226:25,-1,226,-1:29,226:9,184,226:18,-1,226,-1:29,226:2" +
"6,185,226,-1,226,-1:29,226:2,73,226:25,-1,226,-1:29,226,74,226:26,-1,226,-1" +
":29,226:15,75,226:12,-1,226,-1:29,226:5,188,226:22,-1,226,-1:29,190,226:27," +
"-1,226,-1:29,226:11,191,226:16,-1,226,-1:29,226,76,226:26,-1,226,-1:29,226:" +
"8,77,226:19,-1,226,-1:29,226:2,224,226:25,-1,226,-1:29,226:19,192,226:8,-1," +
"226,-1:29,226:8,193,226:19,-1,226,-1:29,226:8,78,226:19,-1,226,-1:29,226:8," +
"79,226:19,-1,226,-1:29,226:2,80,226:25,-1,226,-1:29,226:7,194,226:20,-1,226" +
",-1:29,226:6,195,226:21,-1,226,-1:29,225,226:27,-1,226,-1:29,226:4,197,226:" +
"23,-1,226,-1:29,226:8,81,226:19,-1,226,-1:29,226:7,198,226:20,-1,226,-1:29," +
"226:3,82,226:24,-1,226,-1:29,226:19,83,226:8,-1,226,-1:29,226:11,84,226:16," +
"-1,226,-1:29,226:8,85,226:19,-1,226,-1:29,226:4,107,226:5,109,226,110,226:1" +
"5,-1,226,-1:29,243,226:27,-1,226,-1:29,226:4,145,226:23,-1,226,-1:29,226:15" +
",137,226:12,-1,226,-1:29,226:2,215,226:25,-1,226,-1:29,226,143,226:26,-1,22" +
"6,-1:29,226:11,136,226:16,-1,226,-1:29,226:18,150,226:9,-1,226,-1:29,226:6," +
"216,226:21,-1,226,-1:29,226:7,233,226:20,-1,226,-1:29,226:8,152,226:19,-1,2" +
"26,-1:29,226:2,217,226:25,-1,226,-1:29,226:15,157,226:12,-1,226,-1:29,226:5" +
",160,226:22,-1,226,-1:29,166,226:27,-1,226,-1:29,226:11,238,226:16,-1,226,-" +
"1:29,226:7,162,226:20,-1,226,-1:29,226:12,222,226:15,-1,226,-1:29,226,169,2" +
"26:26,-1,226,-1:29,172,226:27,-1,226,-1:29,226:8,175,226:19,-1,226,-1:29,22" +
"6:7,187,226:20,-1,226,-1:29,226:11,239,226:16,-1,226,-1:29,226:9,186,226:18" +
",-1,226,-1:29,226:8,196,226:19,-1,226,-1:29,226:7,199,226:20,-1,226,-1:29,1" +
"41,226:27,-1,226,-1:29,226:4,213,226:23,-1,226,-1:29,226:15,211,226:12,-1,2" +
"26,-1:29,226,146,226:26,-1,226,-1:29,226:11,207,226:16,-1,226,-1:29,226:7,2" +
"19,226:20,-1,226,-1:29,226:8,164,226:19,-1,226,-1:29,226:2,151,226:25,-1,22" +
"6,-1:29,226:5,163,226:22,-1,226,-1:29,226:11,161,226:16,-1,226,-1:29,226:12" +
",167,226:15,-1,226,-1:29,226,223,226:26,-1,226,-1:29,226:9,189,226:18,-1,22" +
"6,-1:29,226:4,112,226:7,113,226:15,-1,226,-1:29,144,226:27,-1,226,-1:29,226" +
":11,235,226:16,-1,226,-1:29,226:2,153,226:25,-1,226,-1:29,226:11,173,226:16" +
",-1,226,-1:29,226:7,114,226:8,115,226:11,-1,226,-1:29,226:11,147,226:16,-1," +
"226,-1:29,226:2,155,226:25,-1,226,-1:29,226:4,116,226:2,117,226:2,206,203,2" +
"26:16,-1,226,-1:29,226:2,220,226:25,-1,226,-1:29,226:6,229,226:21,-1,226,-1" +
":29,226:8,118,226:19,-1,226,-1:29,226:2,119,226:25,-1,226,-1:29,226:12,231," +
"226:15,-1,226,-1:29,226:2,120,226:2,121,226:14,201,226:7,-1,226,-1:29,226:4" +
",227,226:23,-1,226,-1:29,226:4,122,123,226:6,124,226:15,-1,226,-1:29,205,22" +
"6:3,125,226:6,126,226:16,-1,226,-1:29,226:10,241,226,261,226:15,-1,226,-1:2" +
"9,226:18,202,226:9,-1,226,-1:29,226:7,128,226:20,-1,226,-1:29,249,226:27,-1" +
",226");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ yychar=1;}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.division,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{
    System.err.println("Este es un error lexico: en la linea: "+yyline+", en la columna: "+yychar);
    //Interfaz.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yychar));
}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.por,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{yybegin(ESTADO_CADENA); aux = ""; }
					case -8:
						break;
					case 8:
						{return new Symbol(sym.mas,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.menos,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.modulo,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.parizquierdo,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.parderecho,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.igual,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.menorque,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.mayorque,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.not,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.xor,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.coma,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.puntoycoma,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.llaveizq,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.llaveder,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.dospuntos,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.interrogacion,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.corizquierdo,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.corderecho,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.punto,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.entero,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{yybegin(COMENTARIO_MULTI);}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.masmas,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.menosmenos,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.igualigual,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.menorigualque,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.mayorigualque,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.diferenteque,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.or,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.and,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.rif,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.rdo,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{yychar=1;}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.rint,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.rnew,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.rstr,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.rpow,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.rfor,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.doble,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.caracter,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.rnull,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.rthis,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.rtrue,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.relse,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.rchar,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.rcase,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.rread,yyline,yychar, yytext());}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.rvoid,yyline,yychar, yytext());}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.rtoInt,yyline,yychar, yytext());}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.rbreak,yyline,yychar, yytext());}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.rclass,yyline,yychar, yytext());}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.rgraph,yyline,yychar, yytext());}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.rsuper,yyline,yychar, yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.rprint,yyline,yychar, yytext());}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.rfinal,yyline,yychar, yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.rfalse,yyline,yychar, yytext());}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.rwhile,yyline,yychar, yytext());}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.rimport,yyline,yychar, yytext());}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.rtoChar,yyline,yychar, yytext());}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.rdouble,yyline,yychar, yytext());}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.rreturn,yyline,yychar, yytext());}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.rstring,yyline,yychar, yytext());}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.rstatic,yyline,yychar, yytext());}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.rswitch,yyline,yychar, yytext());}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.rpublic,yyline,yychar, yytext());}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.rdefault,yyline,yychar, yytext());}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.rboolean,yyline,yychar, yytext());}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.rextends,yyline,yychar, yytext());}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.rprintln,yyline,yychar, yytext());}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.rprivate,yyline,yychar, yytext());}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.rtoDouble,yyline,yychar, yytext());}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.rcontinue,yyline,yychar, yytext());}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.rabstract,yyline,yychar, yytext());}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.rread_file,yyline,yychar, yytext());}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.rprotected,yyline,yychar, yytext());}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.rinstanceof,yyline,yychar, yytext());}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.rprinttabla,yyline,yychar, yytext());}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.rwrite_file,yyline,yychar, yytext());}
					case -86:
						break;
					case 86:
						{ yychar=1;}
					case -87:
						break;
					case 87:
						{}
					case -88:
						break;
					case 88:
						{}
					case -89:
						break;
					case 89:
						{yybegin(YYINITIAL);}
					case -90:
						break;
					case 90:
						{aux = aux + yytext(); }
					case -91:
						break;
					case 91:
						{yybegin(YYINITIAL); return new Symbol(sym.cadena,yyline,yychar,aux);}
					case -92:
						break;
					case 93:
						{
    System.err.println("Este es un error lexico: en la linea: "+yyline+", en la columna: "+yychar);
    //Interfaz.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yychar));
}
					case -93:
						break;
					case 94:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -94:
						break;
					case 95:
						{yychar=1;}
					case -95:
						break;
					case 96:
						{}
					case -96:
						break;
					case 98:
						{
    System.err.println("Este es un error lexico: en la linea: "+yyline+", en la columna: "+yychar);
    //Interfaz.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yychar));
}
					case -97:
						break;
					case 99:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -98:
						break;
					case 101:
						{
    System.err.println("Este es un error lexico: en la linea: "+yyline+", en la columna: "+yychar);
    //Interfaz.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yychar));
}
					case -99:
						break;
					case 102:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -100:
						break;
					case 104:
						{
    System.err.println("Este es un error lexico: en la linea: "+yyline+", en la columna: "+yychar);
    //Interfaz.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yychar));
}
					case -101:
						break;
					case 105:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -102:
						break;
					case 107:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -103:
						break;
					case 109:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -104:
						break;
					case 110:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -105:
						break;
					case 111:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -106:
						break;
					case 112:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -107:
						break;
					case 113:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -108:
						break;
					case 114:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -109:
						break;
					case 115:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -110:
						break;
					case 116:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -111:
						break;
					case 117:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -112:
						break;
					case 118:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -113:
						break;
					case 119:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -114:
						break;
					case 120:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -115:
						break;
					case 121:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -116:
						break;
					case 122:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -117:
						break;
					case 123:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -118:
						break;
					case 124:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -119:
						break;
					case 125:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -120:
						break;
					case 126:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -121:
						break;
					case 127:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -122:
						break;
					case 128:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -123:
						break;
					case 129:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -124:
						break;
					case 130:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -125:
						break;
					case 131:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -126:
						break;
					case 132:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -127:
						break;
					case 133:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -128:
						break;
					case 134:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -129:
						break;
					case 135:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -130:
						break;
					case 136:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -131:
						break;
					case 137:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -132:
						break;
					case 138:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -133:
						break;
					case 139:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -134:
						break;
					case 140:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -135:
						break;
					case 141:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -136:
						break;
					case 142:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -137:
						break;
					case 143:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -138:
						break;
					case 144:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -139:
						break;
					case 145:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -140:
						break;
					case 146:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -141:
						break;
					case 147:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -142:
						break;
					case 148:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -143:
						break;
					case 149:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -144:
						break;
					case 150:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -145:
						break;
					case 151:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -146:
						break;
					case 152:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -147:
						break;
					case 153:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -148:
						break;
					case 154:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -149:
						break;
					case 155:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -150:
						break;
					case 156:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -151:
						break;
					case 157:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -152:
						break;
					case 158:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -153:
						break;
					case 159:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -154:
						break;
					case 160:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -155:
						break;
					case 161:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -156:
						break;
					case 162:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -157:
						break;
					case 163:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -158:
						break;
					case 164:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -159:
						break;
					case 165:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -160:
						break;
					case 166:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -161:
						break;
					case 167:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -162:
						break;
					case 168:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -163:
						break;
					case 169:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -164:
						break;
					case 170:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -165:
						break;
					case 171:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -166:
						break;
					case 172:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -167:
						break;
					case 173:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -168:
						break;
					case 174:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -169:
						break;
					case 175:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -170:
						break;
					case 176:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -171:
						break;
					case 177:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -172:
						break;
					case 178:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -173:
						break;
					case 179:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -174:
						break;
					case 180:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -175:
						break;
					case 181:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -176:
						break;
					case 182:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -177:
						break;
					case 183:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -178:
						break;
					case 184:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -179:
						break;
					case 185:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -180:
						break;
					case 186:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -181:
						break;
					case 187:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -182:
						break;
					case 188:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -183:
						break;
					case 189:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -184:
						break;
					case 190:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -185:
						break;
					case 191:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -186:
						break;
					case 192:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -187:
						break;
					case 193:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -188:
						break;
					case 194:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -189:
						break;
					case 195:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -190:
						break;
					case 196:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -191:
						break;
					case 197:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -192:
						break;
					case 198:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -193:
						break;
					case 199:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -194:
						break;
					case 200:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -195:
						break;
					case 201:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -196:
						break;
					case 202:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -197:
						break;
					case 203:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -198:
						break;
					case 204:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -199:
						break;
					case 205:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -200:
						break;
					case 206:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -201:
						break;
					case 207:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -202:
						break;
					case 208:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -203:
						break;
					case 209:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -204:
						break;
					case 210:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -205:
						break;
					case 211:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -206:
						break;
					case 212:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -207:
						break;
					case 213:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -208:
						break;
					case 214:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -209:
						break;
					case 215:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -210:
						break;
					case 216:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -211:
						break;
					case 217:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -212:
						break;
					case 218:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -213:
						break;
					case 219:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -214:
						break;
					case 220:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -215:
						break;
					case 221:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -216:
						break;
					case 222:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -217:
						break;
					case 223:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -218:
						break;
					case 224:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -219:
						break;
					case 225:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -220:
						break;
					case 226:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -221:
						break;
					case 227:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -222:
						break;
					case 228:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -223:
						break;
					case 229:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -224:
						break;
					case 230:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -225:
						break;
					case 231:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -226:
						break;
					case 232:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -227:
						break;
					case 233:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -228:
						break;
					case 234:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -229:
						break;
					case 235:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -230:
						break;
					case 236:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -231:
						break;
					case 237:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -232:
						break;
					case 238:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -233:
						break;
					case 239:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -234:
						break;
					case 240:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -235:
						break;
					case 241:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -236:
						break;
					case 242:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -237:
						break;
					case 243:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -238:
						break;
					case 244:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -239:
						break;
					case 245:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -240:
						break;
					case 246:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -241:
						break;
					case 247:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -242:
						break;
					case 248:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -243:
						break;
					case 249:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -244:
						break;
					case 250:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -245:
						break;
					case 251:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -246:
						break;
					case 252:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -247:
						break;
					case 253:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -248:
						break;
					case 254:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -249:
						break;
					case 255:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -250:
						break;
					case 256:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -251:
						break;
					case 257:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -252:
						break;
					case 258:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -253:
						break;
					case 259:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -254:
						break;
					case 260:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -255:
						break;
					case 261:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -256:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}