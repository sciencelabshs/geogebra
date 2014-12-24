package geogebra.common.geogebra3D.kernel3D.commands;

import geogebra.common.kernel.Kernel;
import geogebra.common.kernel.commands.CmdTangent;
import geogebra.common.kernel.geos.GeoElement;
import geogebra.common.kernel.kernelND.GeoConicND;
import geogebra.common.kernel.kernelND.GeoCurveCartesianND;
import geogebra.common.kernel.kernelND.GeoLineND;
import geogebra.common.kernel.kernelND.GeoPointND;

public class CmdTangent3D extends CmdTangent {

	public CmdTangent3D(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected GeoElement[] tangent(String[] labels, GeoPointND a, GeoConicND c) {
		return kernelA.getManager3D().Tangent3D(labels, a, c);
	}

	@Override
	protected GeoElement[] tangent(String[] labels, GeoLineND l, GeoConicND c) {
		return kernelA.getManager3D().Tangent3D(labels, l, c);
	}

	@Override
	protected GeoElement[] tangent(String[] labels, GeoConicND c1, GeoConicND c2) {
		return kernelA.getManager3D().CommonTangents3D(labels, c1, c2);
	}

	@Override
	protected GeoElement tangentToCurve(String label, GeoPointND point,
			GeoCurveCartesianND curve) {
		return kernelA.getManager3D().Tangent3D(label, point, curve);
	}

}
