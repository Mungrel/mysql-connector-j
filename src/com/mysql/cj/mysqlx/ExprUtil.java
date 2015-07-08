/*
  Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FLOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

package com.mysql.cj.mysqlx;

import com.google.protobuf.ByteString;
import com.mysql.cj.mysqlx.protobuf.MysqlxDatatypes.Any;
import com.mysql.cj.mysqlx.protobuf.MysqlxDatatypes.Scalar;
import com.mysql.cj.mysqlx.protobuf.MysqlxExpr.Expr;

/**
 * Utilities to deal with Expr (and related) structures.
 */
public class ExprUtil {
    /**
     * Proto-buf helper to build a LITERAL Expr with a Scalar NULL type.
     */
    public static Expr buildLiteralNullScalar() {
        Scalar nullScalar = Scalar.newBuilder().setType(Scalar.Type.V_NULL).build();
        Any a = Any.newBuilder().setType(Any.Type.SCALAR).setScalar(nullScalar).build();
        return buildLiteralExpr(a);
    }

    /**
     * Proto-buf helper to build a LITERAL Expr with a Scalar DOUBLE type (wrapped in Any).
     */
    public static Expr buildLiteralScalar(double d) {
        Scalar s = Scalar.newBuilder().setType(Scalar.Type.V_DOUBLE).setVDouble(d).build();
        Any a = Any.newBuilder().setType(Any.Type.SCALAR).setScalar(s).build();
        return buildLiteralExpr(a);
    }

    /**
     * Proto-buf helper to build a LITERAL Expr with a Scalar SINT (signed int) type (wrapped in Any).
     */
    public static Expr buildLiteralScalar(long l) {
        Scalar s = Scalar.newBuilder().setType(Scalar.Type.V_SINT).setVSignedInt(l).build();
        Any a = Any.newBuilder().setType(Any.Type.SCALAR).setScalar(s).build();
        return buildLiteralExpr(a);
    }

    /**
     * Proto-buf helper to build a LITERAL Expr with a Scalar STRING type (wrapped in Any).
     */
    public static Expr buildLiteralScalar(String str) {
        Scalar.String sstr = Scalar.String.newBuilder().setValue(ByteString.copyFromUtf8(str)).build();
        Scalar s = Scalar.newBuilder().setType(Scalar.Type.V_STRING).setVString(sstr).build();
        Any a = Any.newBuilder().setType(Any.Type.SCALAR).setScalar(s).build();
        return buildLiteralExpr(a);
    }

    /**
     * Proto-buf helper to build a LITERAL Expr with a Scalar BOOL type (wrapped in Any).
     */
    public static Expr buildLiteralScalar(boolean b) {
        Scalar s = Scalar.newBuilder().setType(Scalar.Type.V_BOOL).setVBool(b).build();
        Any a = Any.newBuilder().setType(Any.Type.SCALAR).setScalar(s).build();
        return buildLiteralExpr(a);
    }

    /**
     * Wrap an Any value in a LITERAL expression.
     */
    private static Expr buildLiteralExpr(Any any) {
        return Expr.newBuilder().setType(Expr.Type.LITERAL).setConstant(any).build();
    }
}