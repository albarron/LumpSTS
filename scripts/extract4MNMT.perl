#!/usr/bin/perl -w

# Modified from
# https://github.com/moses-smt/mosesdecoder/blob/RELEASE-3.0/scripts/generic/extract-factors.pl
# $Id$
#extract-factors.pl: extract only the desired factors from a factored corpus
#usage: extract-factors corpusfile factor-index factor-index ... > outfile
#factor indices start at 0
#factor indices too large ought to be ignored

use strict;

my ($filename, @factors) = @ARGV;
my %indices = map {$_ => 1} @factors;
my $sourceLabel = '<2es> ';

open(INFILE, "<$filename") or die "couldn't open '$filename' for read: $!\n";
while(my $line = <INFILE>)
{
	chop $line;
	print $sourceLabel.join(' ', map {my $i = 0; join('|', grep($indices{$i++}, split(/\|/, $_)))} split(/\s+/, $line)) . "\n";
}
close(INFILE);
