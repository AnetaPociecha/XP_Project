import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MockDocument {

    static final String ARTICLE_CONTENT = "Sekretarz generalny ONZ Antonio Guterres powiedział w piątek, że pandemia koronawirusa uruchomiła na świecie \"tsunami nienawiści, ksenofobii\", chęć znalezienia winnych i \"kozłów ofiarnych\". Zaapelował do mediów społecznościowych o usuwanie szkodliwych treści.\n" +
            "\"Nienawiść do cudzoziemców wzrasta zarówno online, jak i na ulicach, pojawiły się antysemickie teorie spiskowe i ataki na muzułmanów\", a wszystko to jest związane z pandemią koronawirusa - powiedział Guterres.\n" +
            "\"Migranci i uchodźcy są przedstawiani jako źródło zakażeń (...). O starszych osobach, które są najbardziej narażone, robione są memy sugerujące, że są to osoby najbardziej zbędne\" - kontynuował szef ONZ.\n" +
            "Zaapelował do mediów, a zwłaszcza mediów społecznościowych, instytucji edukacyjnych oraz rządów, aby podjęli walkę z takimi zachowaniami oraz kształcili ludzi, których \"internetowy analfabetyzm\" sprawia, że łatwo padają ofiarą ekstremistów siejących w sieci nienawiść i teorie spiskowe.\n" +
            "Wezwał do usuwania z internetu treści \"rasistowskich, mizoginicznych i innych groźnych wpisów\".\n" +
            "\"Proszę wszystkich, wszędzie, aby przeciwstawili się nienawiści, traktowali się nawzajem z szacunkiem, i korzystali z każdej okazji, by okazać uprzejmość\" - dodał Guterres.\n" +
            "Sekretarz generalny ONZ wezwał też niedawno wszystkie rządy do podjęcia środków przeciwko \"przerażającemu wzrostowi przemocy domowej\".\n" +
            "\"Przemoc nie ogranicza się do pola bitwy - dla wielu kobiet i dziewcząt zagrożenie jest największe tam, gdzie powinny być najbezpieczniejsze: w ich domach\" - podkreślił.\n" +
            "ONZ oceniła, że w niektórych krajach liczba kobiet zwracających się o wsparcie podwoiła się w ciągu ostatnich kilku tygodni, podczas gdy pracownicy służby zdrowia i policja są przytłoczeni zgłoszeniami, a lokalne grupy pomocowe nie mogą działać lub nie mają środków finansowych.";


    static Document get() throws IOException {
        String path = System.getProperty("user.dir").replaceAll("\\\\","/")+"/src/test/java/interia.html";

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            stringBuilder.append(new String(buffer));
            buffer = new char[10];
        }
        reader.close();
        String content = stringBuilder.toString();
        Document document = Jsoup.parse(content);

        return document;
    }
}
