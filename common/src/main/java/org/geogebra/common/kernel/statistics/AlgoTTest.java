/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.statistics;

import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.apache.commons.math3.stat.inference.TTest;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.algos.AlgoElement;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoList;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.util.StringUtil;
import org.geogebra.common.util.debug.Log;

/**
 * Performs a one sample t-test of a mean.
 * 
 * 
 * @author G. Sturr
 */
public class AlgoTTest extends AlgoElement {

	// input
	private GeoList geoList;
	private GeoNumeric hypMean;
	private GeoNumeric mean;
	private GeoNumeric sd;
	private GeoNumeric n;
	private GeoText tail;

	private GeoList result; // output
	private TTest tTestImpl;
	private double[] val;
	private double p;
	private double testStat;

	/**
	 * @param cons
	 *            construction
	 * @param label
	 *            output label
	 * @param geoList
	 *            sample
	 * @param hypMean
	 *            hypothesised mean
	 * @param tail
	 *            one of &lt;,&gt;,=
	 */
	public AlgoTTest(Construction cons, String label, GeoList geoList,
			GeoNumeric hypMean, GeoText tail) {
		super(cons);
		this.geoList = geoList;
		this.hypMean = hypMean;
		this.tail = tail;
		this.mean = null;
		this.sd = null;
		this.n = null;
		result = new GeoList(cons);
		setInputOutput(); // for AlgoElement

		compute();
		result.setLabel(label);
	}

	/**
	 * @param cons
	 *            construction
	 * @param mean
	 *            sample mean
	 * @param sd
	 *            sample standard deviation
	 * @param n
	 *            sample size
	 * @param hypMean
	 *            hypothesised mean
	 * @param tail
	 *            one of &lt;,&gt;,=
	 */
	public AlgoTTest(Construction cons, GeoNumeric mean, GeoNumeric sd,
			GeoNumeric n, GeoNumeric hypMean, GeoText tail) {
		super(cons);
		this.geoList = null;
		this.hypMean = hypMean;
		this.tail = tail;
		this.mean = mean;
		this.sd = sd;
		this.n = n;
		result = new GeoList(cons);
		setInputOutput(); // for AlgoElement

		compute();
	}

	@Override
	public Commands getClassName() {
		return Commands.TTest;
	}

	@Override
	protected void setInputOutput() {

		if (geoList != null) {
			input = new GeoElement[3];
			input[0] = geoList;
			input[1] = hypMean;
			input[2] = tail;

		} else {
			input = new GeoElement[5];
			input[0] = mean;
			input[1] = sd;
			input[2] = n;
			input[3] = hypMean;
			input[4] = tail;
		}

		setOnlyOutput(result);
		setDependencies(); // done by AlgoElement
	}

	/**
	 * @return resulting list
	 */
	public GeoList getResult() {
		return result;
	}

	private double adjustedPValue(double pValue, double testStatistic) {
		return adjustedPValue(pValue, testStatistic, tail);
	}

	/**
	 * @param pValue p-value
	 * @param testStatistic test statistic
	 * @param tail tail string (one of &lt;, &gt; ,&lt;&gt; )
	 * @return adjusted p-value
	 */
	public static double adjustedPValue(double pValue, double testStatistic, GeoText tail) {
		// two sided test
		if (StringUtil.isNotEqual(tail.getTextString())) {
			return pValue;
		}

		// one sided test
		else if ((tail.getTextStringSafe().equals(">") && testStatistic > 0)
				|| (tail.getTextStringSafe().equals("<") && testStatistic < 0)) {
			return pValue / 2;
		} else {
			return 1 - pValue / 2;
		}
	}

	@Override
	public final void compute() {

		if (!(StringUtil.isInequality(tail.getTextString()))) {
			result.setUndefined();
			return;
		}

		// sample data input
		if (input.length == 3) {

			int size = geoList.size();
			if (!geoList.isDefined() || size < 2) {
				result.setUndefined();
				return;
			}

			val = new double[size];

			for (int i = 0; i < size; i++) {
				GeoElement geo = geoList.get(i);
				if (geo instanceof NumberValue) {
					val[i] = geo.evaluateDouble();

				} else {
					result.setUndefined();
					return;
				}
			}

			try {

				// get the test statistic and p
				if (tTestImpl == null) {
					tTestImpl = new TTest();
				}
				testStat = tTestImpl.t(hypMean.getDouble(), val);
				p = tTestImpl.tTest(hypMean.getDouble(), val);
				p = adjustedPValue(p, testStat);

				// put these results into the output list
				result.clear();
				result.addNumber(p, null);
				result.addNumber(testStat, null);

			} catch (RuntimeException e) {
				// catches ArithmeticException, IllegalStateException and
				// ArithmeticException
				Log.debug(e);
			}

			// sample statistics input
		} else {

			// check for valid standard deviation and sample size
			if (sd.getDouble() < 0 || n.getDouble() < 2) {
				result.setUndefined();
				return;
			}

			try {
				StatisticalSummaryValues sumStats = new StatisticalSummaryValues(
						mean.getDouble(), sd.getDouble() * sd.getDouble(),
						(long) n.getDouble(), -1, -1, -1);

				// get the test statistic and p
				if (tTestImpl == null) {
					tTestImpl = new TTest();
				}
				testStat = tTestImpl.t(hypMean.getDouble(), sumStats);
				p = tTestImpl.tTest(hypMean.getDouble(), sumStats);
				p = adjustedPValue(p, testStat);

				// put these results into the output list
				result.clear();
				result.addNumber(p, null);
				result.addNumber(testStat, null);

			} catch (IllegalArgumentException e) {
				Log.debug(e);
				result.setUndefined();
				return;

			} catch (RuntimeException e) {
				// catches ArithmeticException, IllegalStateException and
				// ArithmeticException
				Log.debug(e);
				result.setUndefined();
				return;
			}

		}

	}

}
