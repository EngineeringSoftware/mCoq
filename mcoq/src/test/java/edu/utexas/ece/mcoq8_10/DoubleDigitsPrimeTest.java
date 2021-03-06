package edu.utexas.ece.mcoq8_10;

import de.tudresden.inf.lat.jsexp.Sexp;
import de.tudresden.inf.lat.jsexp.SexpParserException;
import edu.utexas.ece.mcoq8_10.location.MutationLocation;
import edu.utexas.ece.mcoq8_10.mutation.ReplaceZeroWithOne;
import edu.utexas.ece.mcoq8_10.util.SexpUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static edu.utexas.ece.mcoq8_10.util.SexpUtils.getSexpList;

/**
 * @author Ahmet Celik <ahmetcelik@utexas.edu>
 * @author Marinela Parovic <marinelaparovic@gmail.com>
 */
public class DoubleDigitsPrimeTest extends MutationsTest {
    private List<String> allBeforeLines;

    @Before
    public void setUp() throws IOException {
        allBeforeLines = Files.readAllLines(Paths.get(resourceFilePath("DoubleDigitsPrime8_10/DigitsPrime_before.sexp")));
    }

    /* Tests mutation ReplaceZeroWithOne applying mutation to second location and comparing .sexp files. */
    @Test
    public void testBeforeAfterRZWO() throws IOException, SexpParserException {
        MutationLocation MutationLocation = new MutationLocation();
        int allMutationCount = MutationLocation.count(allBeforeLines, new ReplaceZeroWithOne(), true);
        Assert.assertEquals(5, allMutationCount);
        int mutationCount = MutationLocation.count(allBeforeLines, new ReplaceZeroWithOne(), false);
        Assert.assertEquals(1, mutationCount);

        List<Sexp> allBeforeSexps = new ArrayList<>();
        SexpUtils.mutateLines(allBeforeLines, new ReplaceZeroWithOne(), 0, allBeforeSexps::add, true);

        List<Sexp> allAfterSexps = getSexpList(Paths.get(resourceFilePath("DoubleDigitsPrime8_10/DigitsPrime_afterRZWO0.sexp")));

        Assert.assertEquals(allAfterSexps.size(), allBeforeSexps.size());
        Assert.assertEquals(allBeforeSexps, allAfterSexps);
    }
}
