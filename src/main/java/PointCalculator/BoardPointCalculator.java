package PointCalculator;

import Board.Board;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Fetcher.StructuredEncircledAreaFetcher;
import PointCalculator.EncircledArea.Fetcher.StructuredEncircledAreaFetcherImplem;
import PointCalculator.EncircledArea.Validator.EncircledAreaValidator;
import PointCalculator.EncircledArea.Validator.RootValidator;
import PointCalculator.EncircledArea.Validator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.List;

public class BoardPointCalculator {

    private Board b;
    private EncircledAreaValidator rootValidator;
    private EncircledAreaValidator takableRootValidator;
    private EncircledAreaValidator takableChildValidator;


    /**
     *
     * @param b le board à partir duquel calculer
     * @param rootValidator un validateur disnat qu'un élément est une racine
     * @param takableRootValidator un validateur appliqué aux zones racines informant si chacune est prennable
     * @param takableChildValidator un validateur appliqué aux enfants informant si chacune est prennable
     */
    public BoardPointCalculator(Board b,
                                EncircledAreaValidator rootValidator,
                                EncircledAreaValidator takableRootValidator,
                                EncircledAreaValidator takableChildValidator
    ) {
        this.b = b;
        this.rootValidator = rootValidator;
        this.takableRootValidator = takableRootValidator;
        this.takableChildValidator = takableChildValidator;
    }

    public PlayersScoreStats calculate() {
        StructuredEncircledAreaFetcher structureFetcher = new StructuredEncircledAreaFetcherImplem(
                b, this.rootValidator
        );
        List<EncircledArea> rootElements = structureFetcher.fetch();
        PointCalculatorFromEncircledAreas pointCalculatorFromEncircledAreas = new PointCalculatorFromEncircledAreas(
                b,
                rootElements,
                takableRootValidator,
                takableChildValidator
        );
        return pointCalculatorFromEncircledAreas.calculatePlayersScore();
    }
}
