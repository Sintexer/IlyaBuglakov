package com.ilyabuglakov.stringmanipulator.service.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceServiceTest {

    private ReplaceService replacer = new ReplaceService();

    @Test
    void replaceSymbols() {
        int pos = 3;
        char replaceChar = 'A';
        final String content = "asde simple example an ent derer elba";
        final String result = "asdA simAle exaAple an ent derAr elbA";
        assertEquals(result, replacer.replaceSymbols(content, pos, replaceChar));
    }

    @Test
    void replaceMistakeDontReplace(){
        final String content = "po po po apo po";
        assertEquals(content, replacer.replaceMistake(content, 'p', 'o', 'a'));
    }

    @Test
    void replaceMistakeAlreadyCorrected(){
        final String content = "pa apaa apa pa pa";
        assertEquals(content, replacer.replaceMistake(content, 'p', 'o', 'a'));
    }

    @Test
    void replaceMistake(){
        final String content = "ana po alopoa epon po repo repon";
        final String result = "ana po alopaa epan po repo repan";
        assertEquals(result, replacer.replaceMistake(content, 'p', 'o', 'a'));
    }

    @Test
    void replaceWords() {
        String source = "hub gita ram ram nata lena asd, as er aadad, fvc";
        int length = 3;
        String replacement = "leather";
        String result = "leather gita leather leather nata lena leather, as er aadad, leather";
        assertEquals(result, replacer.replaceWords(source, length, replacement));
    }
}